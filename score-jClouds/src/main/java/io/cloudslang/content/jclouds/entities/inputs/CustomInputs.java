package io.cloudslang.content.jclouds.entities.inputs;

import io.cloudslang.content.jclouds.entities.Architecture;
import io.cloudslang.content.jclouds.entities.BlockDeviceMappingStatus;
import io.cloudslang.content.jclouds.entities.VolumeType;
import io.cloudslang.content.jclouds.utils.InputsUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Mihai Tusa.
 * 2/18/2016.
 */
public class CustomInputs {
    private static final String DEFAULT_AMAZON_REGION = "us-east-1";
    private static final String SELF = "self";

    private String region;
    private String instanceId;
    private String imageId;
    private String identityId;
    private String volumeId;
    private String groupId;
    private String hostId;
    private String kernelId;
    private String ownerId;
    private String ramdiskId;
    private String reservationId;
    private String subnetId;
    private String vpcId;
    private String allocationId;
    private String associationId;
    private String architecture;
    private String deleteOnTermination;
    private String blockMappingDeviceName;
    private String blockDeviceMappingSnapshotId;
    private String attachTime;
    private String blockDeviceMappingStatus;
    private String volumeSize;
    private String volumeType;

    public CustomInputs(CustomInputsBuilder builder) {
        this.region = builder.region;
        this.instanceId = builder.instanceId;
        this.imageId = builder.imageId;
        this.identityId = builder.identityId;
        this.volumeId = builder.volumeId;
        this.groupId = builder.groupId;
        this.hostId = builder.hostId;
        this.kernelId = builder.kernelId;
        this.ownerId = builder.ownerId;
        this.ramdiskId = builder.ramdiskId;
        this.reservationId = builder.reservationId;
        this.subnetId = builder.subnetId;
        this.vpcId = builder.vpcId;
        this.allocationId = builder.allocationId;
        this.associationId = builder.associationId;
        this.architecture = builder.architecture;
        this.blockMappingDeviceName = builder.blockMappingDeviceName;
        this.deleteOnTermination = builder.deleteOnTermination;
        this.blockDeviceMappingSnapshotId = builder.blockDeviceMappingSnapshotId;
        this.attachTime = builder.attachTime;
        this.blockDeviceMappingStatus = builder.blockDeviceMappingStatus;
        this.volumeSize = builder.volumeSize;
        this.volumeType = builder.volumeType;
    }

    public String getRegion() {
        return region;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getImageId() {
        return imageId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getHostId() {
        return hostId;
    }

    public String getKernelId() {
        return kernelId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getRamdiskId() {
        return ramdiskId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public String getAssociationId() {
        return associationId;
    }

    public String getArchitecture() {
        return architecture;
    }

    public String getBlockMappingDeviceName() {
        return blockMappingDeviceName;
    }

    public String getDeleteOnTermination() {
        return deleteOnTermination;
    }

    public String getBlockDeviceMappingSnapshotId() {
        return blockDeviceMappingSnapshotId;
    }

    public String getAttachTime() {
        return attachTime;
    }

    public String getBlockDeviceMappingStatus() {
        return blockDeviceMappingStatus;
    }

    public String getVolumeSize() {
        return volumeSize;
    }

    public String getVolumeType() {
        return volumeType;
    }

    public static class CustomInputsBuilder {
        private String region;
        private String instanceId;
        private String imageId;
        private String identityId;
        private String volumeId;
        private String groupId;
        private String hostId;
        private String kernelId;
        private String ownerId;
        private String ramdiskId;
        private String reservationId;
        private String subnetId;
        private String vpcId;
        private String allocationId;
        private String associationId;
        private String architecture;
        private String blockMappingDeviceName;
        private String deleteOnTermination;
        private String blockDeviceMappingSnapshotId;
        private String attachTime;
        private String blockDeviceMappingStatus;
        private String volumeSize;
        private String volumeType;

        public CustomInputs build() {
            return new CustomInputs(this);
        }

        public CustomInputsBuilder withRegion(String inputValue) {
            region = (StringUtils.isBlank(inputValue)) ? DEFAULT_AMAZON_REGION : inputValue;
            return this;
        }

        public CustomInputsBuilder withInstanceId(String inputValue) {
            instanceId = inputValue;
            return this;
        }

        public CustomInputsBuilder withImageId(String inputValue) {
            imageId = inputValue;
            return this;
        }

        public CustomInputsBuilder withIdentityId(String inputValue) {
            identityId = StringUtils.isBlank(inputValue) ? SELF : inputValue;
            return this;
        }

        public CustomInputsBuilder withVolumeId(String inputValue) {
            volumeId = inputValue;
            return this;
        }

        public CustomInputsBuilder withGroupId(String inputValue) {
            groupId = inputValue;
            return this;
        }

        public CustomInputsBuilder withHostId(String inputValue) {
            hostId = inputValue;
            return this;
        }

        public CustomInputsBuilder withKernelId(String inputValue) {
            kernelId = inputValue;
            return this;
        }

        public CustomInputsBuilder withOwnerId(String inputValue) {
            ownerId = inputValue;
            return this;
        }

        public CustomInputsBuilder withRamdiskId(String inputValue) {
            ramdiskId = inputValue;
            return this;
        }

        public CustomInputsBuilder withReservationId(String inputValue) {
            reservationId = inputValue;
            return this;
        }

        public CustomInputsBuilder withSubnetId(String inputValue) {
            subnetId = inputValue;
            return this;
        }

        public CustomInputsBuilder withVpcId(String inputValue) {
            vpcId = inputValue;
            return this;
        }

        public CustomInputsBuilder withAllocationId(String inputValue) {
            allocationId = inputValue;
            return this;
        }

        public CustomInputsBuilder withAssociationId(String inputValue) {
            associationId = inputValue;
            return this;
        }

        public CustomInputsBuilder withArchitecture(String inputValue) {
            architecture = Architecture.getValue(inputValue);
            return this;
        }

        public CustomInputsBuilder withBlockMappingDeviceName(String inputValue) {
            blockMappingDeviceName = inputValue;
            return this;
        }

        public CustomInputsBuilder withDeleteOnTermination(String inputValue) {
            deleteOnTermination = InputsUtil.getRelevantBooleanString(inputValue);
            return this;
        }

        public CustomInputsBuilder withBlockDeviceMappingSnapshotId(String inputValue) {
            blockDeviceMappingSnapshotId = inputValue;
            return this;
        }

        public CustomInputsBuilder withAttachTime(String inputValue) {
            attachTime = inputValue;
            return this;
        }

        public CustomInputsBuilder withBlockDeviceMappingStatus(String inputValue) {
            blockDeviceMappingStatus = BlockDeviceMappingStatus.getValue(inputValue);
            return this;
        }

        public CustomInputsBuilder withVolumeSize(String inputValue) {
            volumeSize = InputsUtil.getValidVolumeAmount(inputValue);
            return this;
        }

        public CustomInputsBuilder withVolumeType(String inputValue) throws Exception {
            volumeType = VolumeType.getValue(inputValue);
            return this;
        }
    }
}