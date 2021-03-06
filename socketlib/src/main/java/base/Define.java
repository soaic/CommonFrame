// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: base/define.proto

package base;

public final class Define {
  private Define() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code base.T_OBJ}
   *
   * <pre>
   *&#47;定义平台对象类型
   * </pre>
   */
  public enum T_OBJ
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>O_SYS = 0;</code>
     *
     * <pre>
     *&#47;平台系统，默认id = 10000
     * </pre>
     */
    O_SYS(0, 0),
    /**
     * <code>O_USER = 1;</code>
     *
     * <pre>
     *&#47;企业用户(使用设备的用户)或下单人
     * </pre>
     */
    O_USER(1, 1),
    /**
     * <code>O_MASTER = 2;</code>
     *
     * <pre>
     *&#47;维修师
     * </pre>
     */
    O_MASTER(2, 2),
    /**
     * <code>O_ENT = 3;</code>
     *
     * <pre>
     *&#47;企业圈
     * </pre>
     */
    O_ENT(3, 3),
    /**
     * <code>O_DEV = 4;</code>
     *
     * <pre>
     *&#47;工业设备
     * </pre>
     */
    O_DEV(4, 4),
    /**
     * <code>O_GROUP = 5;</code>
     *
     * <pre>
     *&#47;群组
     * </pre>
     */
    O_GROUP(5, 5),
    /**
     * <code>O_SP = 6;</code>
     *
     * <pre>
     *&#47;服务商(提供设备或维修服务的用户)
     * </pre>
     */
    O_SP(6, 6),
    /**
     * <code>O_MALL = 7;</code>
     *
     * <pre>
     *&#47;商城平台系统，默认id = 70000
     * </pre>
     */
    O_MALL(7, 7),
    /**
     * <code>O_REPORT = 8;</code>
     *
     * <pre>
     *订单
     * </pre>
     */
    O_REPORT(8, 8),
    /**
     * <code>O_BUSINESS = 9;</code>
     *
     * <pre>
     *商家(需要安装服务的客户)
     * </pre>
     */
    O_BUSINESS(9, 9),
    /**
     * <code>O_CONTACT = 10;</code>
     *
     * <pre>
     *设备维保联系人，订单由O_USER下单
     * </pre>
     */
    O_CONTACT(10, 10),
    /**
     * <code>O_CS = 11;</code>
     *
     * <pre>
     *客服
     * </pre>
     */
    O_CS(11, 11),
    ;

    /**
     * <code>O_SYS = 0;</code>
     *
     * <pre>
     *&#47;平台系统，默认id = 10000
     * </pre>
     */
    public static final int O_SYS_VALUE = 0;
    /**
     * <code>O_USER = 1;</code>
     *
     * <pre>
     *&#47;企业用户(使用设备的用户)或下单人
     * </pre>
     */
    public static final int O_USER_VALUE = 1;
    /**
     * <code>O_MASTER = 2;</code>
     *
     * <pre>
     *&#47;维修师
     * </pre>
     */
    public static final int O_MASTER_VALUE = 2;
    /**
     * <code>O_ENT = 3;</code>
     *
     * <pre>
     *&#47;企业圈
     * </pre>
     */
    public static final int O_ENT_VALUE = 3;
    /**
     * <code>O_DEV = 4;</code>
     *
     * <pre>
     *&#47;工业设备
     * </pre>
     */
    public static final int O_DEV_VALUE = 4;
    /**
     * <code>O_GROUP = 5;</code>
     *
     * <pre>
     *&#47;群组
     * </pre>
     */
    public static final int O_GROUP_VALUE = 5;
    /**
     * <code>O_SP = 6;</code>
     *
     * <pre>
     *&#47;服务商(提供设备或维修服务的用户)
     * </pre>
     */
    public static final int O_SP_VALUE = 6;
    /**
     * <code>O_MALL = 7;</code>
     *
     * <pre>
     *&#47;商城平台系统，默认id = 70000
     * </pre>
     */
    public static final int O_MALL_VALUE = 7;
    /**
     * <code>O_REPORT = 8;</code>
     *
     * <pre>
     *订单
     * </pre>
     */
    public static final int O_REPORT_VALUE = 8;
    /**
     * <code>O_BUSINESS = 9;</code>
     *
     * <pre>
     *商家(需要安装服务的客户)
     * </pre>
     */
    public static final int O_BUSINESS_VALUE = 9;
    /**
     * <code>O_CONTACT = 10;</code>
     *
     * <pre>
     *设备维保联系人，订单由O_USER下单
     * </pre>
     */
    public static final int O_CONTACT_VALUE = 10;
    /**
     * <code>O_CS = 11;</code>
     *
     * <pre>
     *客服
     * </pre>
     */
    public static final int O_CS_VALUE = 11;


    public final int getNumber() { return value; }

    public static T_OBJ valueOf(int value) {
      switch (value) {
        case 0: return O_SYS;
        case 1: return O_USER;
        case 2: return O_MASTER;
        case 3: return O_ENT;
        case 4: return O_DEV;
        case 5: return O_GROUP;
        case 6: return O_SP;
        case 7: return O_MALL;
        case 8: return O_REPORT;
        case 9: return O_BUSINESS;
        case 10: return O_CONTACT;
        case 11: return O_CS;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<T_OBJ>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<T_OBJ>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<T_OBJ>() {
            public T_OBJ findValueByNumber(int number) {
              return T_OBJ.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return Define.getDescriptor().getEnumTypes().get(0);
    }

    private static final T_OBJ[] VALUES = values();

    public static T_OBJ valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private T_OBJ(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:base.T_OBJ)
  }

  /**
   * Protobuf enum {@code base.T_DEV}
   */
  public enum T_DEV
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>DEV_AIR = 1001;</code>
     *
     * <pre>
     *&#47;空压机
     * </pre>
     */
    DEV_AIR(0, 1001),
    /**
     * <code>DEV_MOTOR = 1002;</code>
     *
     * <pre>
     *&#47;马达、电机
     * </pre>
     */
    DEV_MOTOR(1, 1002),
    ;

    /**
     * <code>DEV_AIR = 1001;</code>
     *
     * <pre>
     *&#47;空压机
     * </pre>
     */
    public static final int DEV_AIR_VALUE = 1001;
    /**
     * <code>DEV_MOTOR = 1002;</code>
     *
     * <pre>
     *&#47;马达、电机
     * </pre>
     */
    public static final int DEV_MOTOR_VALUE = 1002;


    public final int getNumber() { return value; }

    public static T_DEV valueOf(int value) {
      switch (value) {
        case 1001: return DEV_AIR;
        case 1002: return DEV_MOTOR;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<T_DEV>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<T_DEV>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<T_DEV>() {
            public T_DEV findValueByNumber(int number) {
              return T_DEV.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return Define.getDescriptor().getEnumTypes().get(1);
    }

    private static final T_DEV[] VALUES = values();

    public static T_DEV valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private T_DEV(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:base.T_DEV)
  }

  /**
   * Protobuf enum {@code base.ACC_STATUS}
   *
   * <pre>
   * 平台账户状态(审核状态)
   * </pre>
   */
  public enum ACC_STATUS
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>S_ACC_LOCK = -1;</code>
     *
     * <pre>
     * 封停
     * </pre>
     */
    S_ACC_LOCK(0, -1),
    /**
     * <code>S_ACC_NOR = 0;</code>
     *
     * <pre>
     * 正常
     * </pre>
     */
    S_ACC_NOR(1, 0),
    /**
     * <code>S_ACC_SUBMIT_CERTIFICATION = 1;</code>
     *
     * <pre>
     * 已提交实名认证审核
     * </pre>
     */
    S_ACC_SUBMIT_CERTIFICATION(2, 1),
    /**
     * <code>S_ACC_IDENTIFY_PASS_CERTIFICATION = 2;</code>
     *
     * <pre>
     * 实名认证审核通过
     * </pre>
     */
    S_ACC_IDENTIFY_PASS_CERTIFICATION(3, 2),
    /**
     * <code>S_ACC_IDENTIFY_REJECT_CERTIFICATION = 3;</code>
     *
     * <pre>
     * 实名认证审核驳回
     * </pre>
     */
    S_ACC_IDENTIFY_REJECT_CERTIFICATION(4, 3),
    /**
     * <code>S_ACC_SUBMIT_SKILL = 4;</code>
     *
     * <pre>
     * 已提交技能认证审核
     * </pre>
     */
    S_ACC_SUBMIT_SKILL(5, 4),
    /**
     * <code>S_ACC_IDENTIFY_PASS_SKILL = 5;</code>
     *
     * <pre>
     * 技能认证审核通过
     * </pre>
     */
    S_ACC_IDENTIFY_PASS_SKILL(6, 5),
    /**
     * <code>S_ACC_IDENTIFY_REJECT_SKILL = 6;</code>
     *
     * <pre>
     * 技能认证审核驳回
     * </pre>
     */
    S_ACC_IDENTIFY_REJECT_SKILL(7, 6),
    ;

    /**
     * <code>S_ACC_LOCK = -1;</code>
     *
     * <pre>
     * 封停
     * </pre>
     */
    public static final int S_ACC_LOCK_VALUE = -1;
    /**
     * <code>S_ACC_NOR = 0;</code>
     *
     * <pre>
     * 正常
     * </pre>
     */
    public static final int S_ACC_NOR_VALUE = 0;
    /**
     * <code>S_ACC_SUBMIT_CERTIFICATION = 1;</code>
     *
     * <pre>
     * 已提交实名认证审核
     * </pre>
     */
    public static final int S_ACC_SUBMIT_CERTIFICATION_VALUE = 1;
    /**
     * <code>S_ACC_IDENTIFY_PASS_CERTIFICATION = 2;</code>
     *
     * <pre>
     * 实名认证审核通过
     * </pre>
     */
    public static final int S_ACC_IDENTIFY_PASS_CERTIFICATION_VALUE = 2;
    /**
     * <code>S_ACC_IDENTIFY_REJECT_CERTIFICATION = 3;</code>
     *
     * <pre>
     * 实名认证审核驳回
     * </pre>
     */
    public static final int S_ACC_IDENTIFY_REJECT_CERTIFICATION_VALUE = 3;
    /**
     * <code>S_ACC_SUBMIT_SKILL = 4;</code>
     *
     * <pre>
     * 已提交技能认证审核
     * </pre>
     */
    public static final int S_ACC_SUBMIT_SKILL_VALUE = 4;
    /**
     * <code>S_ACC_IDENTIFY_PASS_SKILL = 5;</code>
     *
     * <pre>
     * 技能认证审核通过
     * </pre>
     */
    public static final int S_ACC_IDENTIFY_PASS_SKILL_VALUE = 5;
    /**
     * <code>S_ACC_IDENTIFY_REJECT_SKILL = 6;</code>
     *
     * <pre>
     * 技能认证审核驳回
     * </pre>
     */
    public static final int S_ACC_IDENTIFY_REJECT_SKILL_VALUE = 6;


    public final int getNumber() { return value; }

    public static ACC_STATUS valueOf(int value) {
      switch (value) {
        case -1: return S_ACC_LOCK;
        case 0: return S_ACC_NOR;
        case 1: return S_ACC_SUBMIT_CERTIFICATION;
        case 2: return S_ACC_IDENTIFY_PASS_CERTIFICATION;
        case 3: return S_ACC_IDENTIFY_REJECT_CERTIFICATION;
        case 4: return S_ACC_SUBMIT_SKILL;
        case 5: return S_ACC_IDENTIFY_PASS_SKILL;
        case 6: return S_ACC_IDENTIFY_REJECT_SKILL;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ACC_STATUS>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ACC_STATUS>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ACC_STATUS>() {
            public ACC_STATUS findValueByNumber(int number) {
              return ACC_STATUS.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return Define.getDescriptor().getEnumTypes().get(2);
    }

    private static final ACC_STATUS[] VALUES = values();

    public static ACC_STATUS valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ACC_STATUS(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:base.ACC_STATUS)
  }

  public interface OBJ_GYBSOrBuilder extends
      // @@protoc_insertion_point(interface_extends:base.OBJ_GYBS)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required .base.T_OBJ type = 1;</code>
     */
    boolean hasType();
    /**
     * <code>required .base.T_OBJ type = 1;</code>
     */
    T_OBJ getType();

    /**
     * <code>required uint32 id = 2;</code>
     */
    boolean hasId();
    /**
     * <code>required uint32 id = 2;</code>
     */
    int getId();
  }
  /**
   * Protobuf type {@code base.OBJ_GYBS}
   */
  public static final class OBJ_GYBS extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:base.OBJ_GYBS)
      OBJ_GYBSOrBuilder {
    // Use OBJ_GYBS.newBuilder() to construct.
    private OBJ_GYBS(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private OBJ_GYBS(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final OBJ_GYBS defaultInstance;
    public static OBJ_GYBS getDefaultInstance() {
      return defaultInstance;
    }

    public OBJ_GYBS getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private OBJ_GYBS(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();
              T_OBJ value = T_OBJ.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                type_ = value;
              }
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              id_ = input.readUInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Define.internal_static_base_OBJ_GYBS_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Define.internal_static_base_OBJ_GYBS_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              OBJ_GYBS.class, Builder.class);
    }

    public static com.google.protobuf.Parser<OBJ_GYBS> PARSER =
        new com.google.protobuf.AbstractParser<OBJ_GYBS>() {
      public OBJ_GYBS parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new OBJ_GYBS(input, extensionRegistry);
      }
    };

    @Override
    public com.google.protobuf.Parser<OBJ_GYBS> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TYPE_FIELD_NUMBER = 1;
    private T_OBJ type_;
    /**
     * <code>required .base.T_OBJ type = 1;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .base.T_OBJ type = 1;</code>
     */
    public T_OBJ getType() {
      return type_;
    }

    public static final int ID_FIELD_NUMBER = 2;
    private int id_;
    /**
     * <code>required uint32 id = 2;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required uint32 id = 2;</code>
     */
    public int getId() {
      return id_;
    }

    private void initFields() {
      type_ = T_OBJ.O_SYS;
      id_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeEnum(1, type_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeUInt32(2, id_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, type_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, id_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static OBJ_GYBS parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static OBJ_GYBS parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static OBJ_GYBS parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static OBJ_GYBS parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static OBJ_GYBS parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static OBJ_GYBS parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static OBJ_GYBS parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static OBJ_GYBS parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static OBJ_GYBS parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static OBJ_GYBS parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(OBJ_GYBS prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code base.OBJ_GYBS}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:base.OBJ_GYBS)
        OBJ_GYBSOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Define.internal_static_base_OBJ_GYBS_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Define.internal_static_base_OBJ_GYBS_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                OBJ_GYBS.class, Builder.class);
      }

      // Construct using base.Define.OBJ_GYBS.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        type_ = T_OBJ.O_SYS;
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Define.internal_static_base_OBJ_GYBS_descriptor;
      }

      public OBJ_GYBS getDefaultInstanceForType() {
        return OBJ_GYBS.getDefaultInstance();
      }

      public OBJ_GYBS build() {
        OBJ_GYBS result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public OBJ_GYBS buildPartial() {
        OBJ_GYBS result = new OBJ_GYBS(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.id_ = id_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof OBJ_GYBS) {
          return mergeFrom((OBJ_GYBS)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(OBJ_GYBS other) {
        if (other == OBJ_GYBS.getDefaultInstance()) return this;
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasId()) {
          setId(other.getId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasType()) {
          
          return false;
        }
        if (!hasId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        OBJ_GYBS parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (OBJ_GYBS) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private T_OBJ type_ = T_OBJ.O_SYS;
      /**
       * <code>required .base.T_OBJ type = 1;</code>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .base.T_OBJ type = 1;</code>
       */
      public T_OBJ getType() {
        return type_;
      }
      /**
       * <code>required .base.T_OBJ type = 1;</code>
       */
      public Builder setType(T_OBJ value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required .base.T_OBJ type = 1;</code>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        type_ = T_OBJ.O_SYS;
        onChanged();
        return this;
      }

      private int id_ ;
      /**
       * <code>required uint32 id = 2;</code>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required uint32 id = 2;</code>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>required uint32 id = 2;</code>
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000002;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required uint32 id = 2;</code>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        id_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:base.OBJ_GYBS)
    }

    static {
      defaultInstance = new OBJ_GYBS(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:base.OBJ_GYBS)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_base_OBJ_GYBS_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_base_OBJ_GYBS_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\021base/define.proto\022\004base\"1\n\010OBJ_GYBS\022\031\n" +
      "\004type\030\001 \002(\0162\013.base.T_OBJ\022\n\n\002id\030\002 \002(\r*\234\001\n" +
      "\005T_OBJ\022\t\n\005O_SYS\020\000\022\n\n\006O_USER\020\001\022\014\n\010O_MASTE" +
      "R\020\002\022\t\n\005O_ENT\020\003\022\t\n\005O_DEV\020\004\022\013\n\007O_GROUP\020\005\022\010" +
      "\n\004O_SP\020\006\022\n\n\006O_MALL\020\007\022\014\n\010O_REPORT\020\010\022\016\n\nO_" +
      "BUSINESS\020\t\022\r\n\tO_CONTACT\020\n\022\010\n\004O_CS\020\013*%\n\005T" +
      "_DEV\022\014\n\007DEV_AIR\020\351\007\022\016\n\tDEV_MOTOR\020\352\007*\374\001\n\nA" +
      "CC_STATUS\022\027\n\nS_ACC_LOCK\020\377\377\377\377\377\377\377\377\377\001\022\r\n\tS_" +
      "ACC_NOR\020\000\022\036\n\032S_ACC_SUBMIT_CERTIFICATION\020" +
      "\001\022%\n!S_ACC_IDENTIFY_PASS_CERTIFICATION\020\002",
      "\022\'\n#S_ACC_IDENTIFY_REJECT_CERTIFICATION\020" +
      "\003\022\026\n\022S_ACC_SUBMIT_SKILL\020\004\022\035\n\031S_ACC_IDENT" +
      "IFY_PASS_SKILL\020\005\022\037\n\033S_ACC_IDENTIFY_REJEC" +
      "T_SKILL\020\006"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_base_OBJ_GYBS_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_base_OBJ_GYBS_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_base_OBJ_GYBS_descriptor,
        new String[] { "Type", "Id", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
