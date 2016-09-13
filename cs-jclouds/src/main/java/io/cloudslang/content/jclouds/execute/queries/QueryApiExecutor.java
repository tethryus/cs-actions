package io.cloudslang.content.jclouds.execute.queries;

import io.cloudslang.content.httpclient.CSHttpClient;
import io.cloudslang.content.jclouds.entities.aws.AuthorizationHeader;
import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.entities.inputs.CommonInputs;
import io.cloudslang.content.jclouds.entities.inputs.InputsWrapper;
import io.cloudslang.content.jclouds.factory.InputsWrapperFactory;
import io.cloudslang.content.jclouds.factory.ParamsMapFactory;
import io.cloudslang.content.jclouds.services.AmazonSignatureService;
import io.cloudslang.content.jclouds.utils.InputsUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihai Tusa.
 * 9/6/2016.
 */
public class QueryApiExecutor {
    @SafeVarargs
    public final <T> Map<String, String> execute(CommonInputs commonInputs, T... builders)
            throws MalformedURLException, SignatureException {
        InputsWrapper inputs = InputsWrapperFactory.getWrapper(commonInputs, builders);

        Map<String, String> headersMap = StringUtils.isNotBlank(inputs.getCommonInputs().getHeaders()) ?
                InputsUtil.getHeadersOrQueryParamsMap(new HashMap<String, String>(), inputs.getCommonInputs().getHeaders(),
                        Constants.AwsParams.HEADER_DELIMITER, Constants.Miscellaneous.COLON, true) :
                new HashMap<String, String>();

        Map<String, String> queryParamsMap = ParamsMapFactory.getQueryApiParamsMap(inputs);

        setQueryApiCallParams(inputs, queryParamsMap);
        setQueryApiCallHeaders(inputs, headersMap, queryParamsMap);

        return new CSHttpClient().execute(inputs.getHttpClientInputs());
    }

    void setQueryApiCallHeaders(InputsWrapper inputs, Map<String, String> headersMap, Map<String, String> queryParamsMap)
            throws SignatureException, MalformedURLException {
        AuthorizationHeader signedHeaders = new AmazonSignatureService().signRequestHeaders(inputs, headersMap, queryParamsMap);
        inputs.getHttpClientInputs().setHeaders(signedHeaders.getAuthorizationHeader());
    }

    private void setQueryApiCallParams(InputsWrapper inputs, Map<String, String> queryParamsMap) {
        String queryParamsString = InputsUtil
                .getParamsString(queryParamsMap, Constants.Miscellaneous.EQUAL, Constants.Miscellaneous.AMPERSAND);
        inputs.getHttpClientInputs().setQueryParams(queryParamsString);
    }
}