package com.hp.score.content.ssh.utils;

import com.hp.score.content.ssh.entities.SSHConnection;
import com.hp.oo.sdk.content.plugin.GlobalSessionObject;
//import com.hp.oo.sdk.content.plugin.SessionObject;
import com.hp.oo.sdk.content.plugin.SessionParam;
import com.hp.oo.sdk.content.plugin.SessionResource;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ioanvranauhp
 * Date: 10/29/14
 */
public class CacheUtils {
    /**
     * @param sessionParam the cache.
     * @return the SSH session from cache
     */
    public static Session getSshSession(SessionParam sessionParam, String sessionId) {
        SessionResource resource = getSessionResource(sessionParam);
        if (resource != null) {
            Object obj = resource.get();
            if (obj != null && obj instanceof Map) {
                SSHConnection sshConnection = (SSHConnection) ((Map) obj).get(sessionId);
                if (sshConnection != null) {
                    return sshConnection.getSession();
                }
            }
        }

        return null;
    }

    /**
     * @param sessionParam the cache.
     * @return the SSH channel from cache
     */
    public static Channel getSshChannel(SessionParam sessionParam, String sessionId) {
        SessionResource resource = getSessionResource(sessionParam);
        if (resource != null) {
            Object obj = resource.get();
            if (obj != null && obj instanceof Map) {
                SSHConnection sshConnection = (SSHConnection) ((Map) obj).get(sessionId);
                if (sshConnection != null) {
                    return sshConnection.getChannel();
                }
            }
        }

        return null;
    }

    private static SessionResource getSessionResource(SessionParam sessionParam) {
        SessionResource resource = null;
        if (sessionParam instanceof GlobalSessionObject<?>) {
            resource = ((GlobalSessionObject) sessionParam).getResource();
        } else if (sessionParam instanceof GlobalSessionObject<?>) { //TODO sessionObject?
            resource = ((GlobalSessionObject) sessionParam).getResource();//TODO sessionObject?
        }

        return resource;
    }

    /**
     * Save the SSH session in the cache.
     *
     * @param session      The SSH session.
     * @param sessionParam The cache: GlobalSessionObject or SessionObject.
     */
    public static void saveSshSession(Session session, SessionParam sessionParam, String sessionId) {
        if (sessionParam instanceof GlobalSessionObject<?>) {
            GlobalSessionObject<Map<String, SSHConnection>> globalSessionObject = (GlobalSessionObject) sessionParam;
            Map<String, SSHConnection> tempMap = globalSessionObject.get();
            if (tempMap == null) {
                tempMap = new HashMap<String, SSHConnection>();
            }
            tempMap.put(sessionId, new SSHConnection(session));
            globalSessionObject.setResource(new SSHSessionResource(tempMap));
        } else if (sessionParam instanceof GlobalSessionObject<?>) {//TODO sessionObject?
            GlobalSessionObject<Map<String, SSHConnection>> sessionObject = ((GlobalSessionObject) sessionParam);//TODO sessionObject?
            Map<String, SSHConnection> tempMap = sessionObject.get();
            if (tempMap == null) {
                tempMap = new HashMap<String, SSHConnection>();
            }
            tempMap.put(sessionId, new SSHConnection(session));
            sessionObject.setResource(new SSHSessionResource(tempMap));
        } else {
            throw new RuntimeException("The SSH session could not be saved in the given sessionParam.");
        }
    }

    /**
     * Save the SSH session and the channel in the cache.
     *
     * @param session      The SSH session.
     * @param channel      The SSH channel.
     * @param sessionParam The cache: GlobalSessionObject or SessionObject.
     */
    public static void saveSshSessionAndChannel(Session session, Channel channel, SessionParam sessionParam, String sessionId) {
        if (sessionParam instanceof GlobalSessionObject<?>) {
            GlobalSessionObject<Map<String, SSHConnection>> globalSessionObject = (GlobalSessionObject) sessionParam;
            Map<String, SSHConnection> tempMap = globalSessionObject.get();
            if (tempMap == null) {
                tempMap = new HashMap<>();
            }
            tempMap.put(sessionId, new SSHConnection(session, channel));
            globalSessionObject.setResource(new SSHSessionResource(tempMap));
        } else if (sessionParam instanceof GlobalSessionObject<?>) {//TODO sessionObject?
            GlobalSessionObject<Map<String, SSHConnection>> sessionObject = ((GlobalSessionObject) sessionParam);//TODO sessionObject?
            Map<String, SSHConnection> tempMap = sessionObject.get();
            if (tempMap == null) {
                tempMap = new HashMap<>();
            }
            tempMap.put(sessionId, new SSHConnection(session, channel));
            sessionObject.setResource(new SSHSessionResource(tempMap));
        } else {
            throw new RuntimeException("The SSH session and SSh channel could not be saved in the given sessionParam.");
        }
    }

    /**
     * Remove the SSH session (and associated channel if any) from the cache.
     *
     * @param sessionParam The cache.
     * @param sessionId    The key to the session in the cache map.
     */
    public static void removeSshSession(SessionParam sessionParam, String sessionId) {
        SessionResource resource = null;
        if (sessionParam instanceof GlobalSessionObject<?>) {
            resource = ((GlobalSessionObject) sessionParam).getResource();
        } else {
            resource = ((GlobalSessionObject) sessionParam).getResource();//TODO sessionObject?
        }

        if (resource != null) {
            Map<String, SSHConnection> tempMap = (Map<String, SSHConnection>) resource.get();
            if (tempMap != null) {
                if (sessionParam instanceof GlobalSessionObject<?>) {
                    tempMap.remove(sessionId);
                } else {
                    decrementSessionsCounter(sessionId, tempMap);
                }

            }
        }
    }

    private static void decrementSessionsCounter(String sessionId, Map<String, SSHConnection> tempMap) {
        SSHConnection connection = tempMap.get(sessionId);
        synchronized (connection) {
            if (connection.getSessionsCounter() == 0) {
                tempMap.remove(sessionId);
            } else {
                int oldValue = connection.getSessionsCounter();
                connection.setSessionsCounter(--oldValue);
            }
        }
    }

    /**
     * Wrapper class on SSH session.
     */
    private static class SSHSessionResource extends SessionResource<Map<String, SSHConnection>> {
        private Map<String, SSHConnection> sshConnectionMap;

        public SSHSessionResource(Map<String, SSHConnection> sshConnectionMap) {
            this.sshConnectionMap = sshConnectionMap;
        }

        @Override
        public Map<String, SSHConnection> get() {
            return sshConnectionMap;
        }

        @Override
        public void release() {
            for (Object value : sshConnectionMap.values()) {
                SSHConnection sshConnection = (SSHConnection) value;
                int sessionsCounter = sshConnection.getSessionsCounter();
                synchronized (sshConnection) {
                    if (sshConnection != null) {
                        Session session = sshConnection.getSession();
                        session.disconnect();
                        Channel channel = sshConnection.getChannel();
                        if (channel != null) {
                            channel.disconnect();
                        }
                    }
                }
            }
            sshConnectionMap = null;
        }
    }
}
