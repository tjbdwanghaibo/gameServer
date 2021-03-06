// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MsgTest.proto

package org.jow.common.msg;

public final class MsgTest {
  private MsgTest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CSGmCmdOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string command = 1;
    /**
     * <code>required string command = 1;</code>
     *
     * <pre>
     * GM命令
     * </pre>
     */
    boolean hasCommand();
    /**
     * <code>required string command = 1;</code>
     *
     * <pre>
     * GM命令
     * </pre>
     */
    java.lang.String getCommand();
    /**
     * <code>required string command = 1;</code>
     *
     * <pre>
     * GM命令
     * </pre>
     */
    com.google.protobuf.ByteString
        getCommandBytes();
  }
  /**
   * Protobuf type {@code org.jow.common.msg.CSGmCmd}
   */
  public static final class CSGmCmd extends
      com.google.protobuf.GeneratedMessage
      implements CSGmCmdOrBuilder {
    // Use CSGmCmd.newBuilder() to construct.
    private CSGmCmd(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CSGmCmd(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CSGmCmd defaultInstance;
    public static CSGmCmd getDefaultInstance() {
      return defaultInstance;
    }

    public CSGmCmd getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CSGmCmd(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
            case 10: {
              bitField0_ |= 0x00000001;
              command_ = input.readBytes();
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
      return org.jow.common.msg.MsgTest.internal_static_org_jow_common_msg_CSGmCmd_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.jow.common.msg.MsgTest.internal_static_org_jow_common_msg_CSGmCmd_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.jow.common.msg.MsgTest.CSGmCmd.class, org.jow.common.msg.MsgTest.CSGmCmd.Builder.class);
    }

    public static com.google.protobuf.Parser<CSGmCmd> PARSER =
        new com.google.protobuf.AbstractParser<CSGmCmd>() {
      public CSGmCmd parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CSGmCmd(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CSGmCmd> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string command = 1;
    public static final int COMMAND_FIELD_NUMBER = 1;
    private java.lang.Object command_;
    /**
     * <code>required string command = 1;</code>
     *
     * <pre>
     * GM命令
     * </pre>
     */
    public boolean hasCommand() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string command = 1;</code>
     *
     * <pre>
     * GM命令
     * </pre>
     */
    public java.lang.String getCommand() {
      java.lang.Object ref = command_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          command_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string command = 1;</code>
     *
     * <pre>
     * GM命令
     * </pre>
     */
    public com.google.protobuf.ByteString
        getCommandBytes() {
      java.lang.Object ref = command_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        command_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      command_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasCommand()) {
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
        output.writeBytes(1, getCommandBytes());
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
          .computeBytesSize(1, getCommandBytes());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.jow.common.msg.MsgTest.CSGmCmd parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.jow.common.msg.MsgTest.CSGmCmd prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code org.jow.common.msg.CSGmCmd}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements org.jow.common.msg.MsgTest.CSGmCmdOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.jow.common.msg.MsgTest.internal_static_org_jow_common_msg_CSGmCmd_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.jow.common.msg.MsgTest.internal_static_org_jow_common_msg_CSGmCmd_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.jow.common.msg.MsgTest.CSGmCmd.class, org.jow.common.msg.MsgTest.CSGmCmd.Builder.class);
      }

      // Construct using org.jow.common.msg.MsgTest.CSGmCmd.newBuilder()
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
        command_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.jow.common.msg.MsgTest.internal_static_org_jow_common_msg_CSGmCmd_descriptor;
      }

      public org.jow.common.msg.MsgTest.CSGmCmd getDefaultInstanceForType() {
        return org.jow.common.msg.MsgTest.CSGmCmd.getDefaultInstance();
      }

      public org.jow.common.msg.MsgTest.CSGmCmd build() {
        org.jow.common.msg.MsgTest.CSGmCmd result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.jow.common.msg.MsgTest.CSGmCmd buildPartial() {
        org.jow.common.msg.MsgTest.CSGmCmd result = new org.jow.common.msg.MsgTest.CSGmCmd(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.command_ = command_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.jow.common.msg.MsgTest.CSGmCmd) {
          return mergeFrom((org.jow.common.msg.MsgTest.CSGmCmd)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.jow.common.msg.MsgTest.CSGmCmd other) {
        if (other == org.jow.common.msg.MsgTest.CSGmCmd.getDefaultInstance()) return this;
        if (other.hasCommand()) {
          bitField0_ |= 0x00000001;
          command_ = other.command_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCommand()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.jow.common.msg.MsgTest.CSGmCmd parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.jow.common.msg.MsgTest.CSGmCmd) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string command = 1;
      private java.lang.Object command_ = "";
      /**
       * <code>required string command = 1;</code>
       *
       * <pre>
       * GM命令
       * </pre>
       */
      public boolean hasCommand() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string command = 1;</code>
       *
       * <pre>
       * GM命令
       * </pre>
       */
      public java.lang.String getCommand() {
        java.lang.Object ref = command_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          command_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string command = 1;</code>
       *
       * <pre>
       * GM命令
       * </pre>
       */
      public com.google.protobuf.ByteString
          getCommandBytes() {
        java.lang.Object ref = command_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          command_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string command = 1;</code>
       *
       * <pre>
       * GM命令
       * </pre>
       */
      public Builder setCommand(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        command_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string command = 1;</code>
       *
       * <pre>
       * GM命令
       * </pre>
       */
      public Builder clearCommand() {
        bitField0_ = (bitField0_ & ~0x00000001);
        command_ = getDefaultInstance().getCommand();
        onChanged();
        return this;
      }
      /**
       * <code>required string command = 1;</code>
       *
       * <pre>
       * GM命令
       * </pre>
       */
      public Builder setCommandBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        command_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:org.jow.common.msg.CSGmCmd)
    }

    static {
      defaultInstance = new CSGmCmd(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:org.jow.common.msg.CSGmCmd)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_org_jow_common_msg_CSGmCmd_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_org_jow_common_msg_CSGmCmd_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rMsgTest.proto\022\022org.jow.common.msg\032\ropt" +
      "ions.proto\032\014MsgDef.proto\"!\n\007CSGmCmd\022\017\n\007c" +
      "ommand\030\001 \002(\t:\005\210\361\004\255M"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_org_jow_common_msg_CSGmCmd_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_org_jow_common_msg_CSGmCmd_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_org_jow_common_msg_CSGmCmd_descriptor,
              new java.lang.String[] { "Command", });
          com.google.protobuf.ExtensionRegistry registry =
            com.google.protobuf.ExtensionRegistry.newInstance();
          registry.add(org.jow.common.msg.Options.msgid);
          return registry;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.jow.common.msg.Options.getDescriptor(),
          org.jow.common.msg.MsgDef.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
