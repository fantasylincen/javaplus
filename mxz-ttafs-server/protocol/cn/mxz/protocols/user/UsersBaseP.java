// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: users_base.p

package cn.mxz.protocols.user;

public final class UsersBaseP {
  private UsersBaseP() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface UserBaseSProOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // repeated .cn.mxz.protocols.user.UserBasePro users = 1;
    java.util.List<cn.mxz.protocols.user.UserBaseP.UserBasePro> 
        getUsersList();
    cn.mxz.protocols.user.UserBaseP.UserBasePro getUsers(int index);
    int getUsersCount();
    java.util.List<? extends cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder> 
        getUsersOrBuilderList();
    cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder getUsersOrBuilder(
        int index);
  }
  public static final class UserBaseSPro extends
      com.google.protobuf.GeneratedMessage
      implements UserBaseSProOrBuilder {
    // Use UserBaseSPro.newBuilder() to construct.
    private UserBaseSPro(Builder builder) {
      super(builder);
    }
    private UserBaseSPro(boolean noInit) {}
    
    private static final UserBaseSPro defaultInstance;
    public static UserBaseSPro getDefaultInstance() {
      return defaultInstance;
    }
    
    @Override
	public UserBaseSPro getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cn.mxz.protocols.user.UsersBaseP.internal_static_cn_mxz_protocols_user_UserBaseSPro_descriptor;
    }
    
    @Override
	protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cn.mxz.protocols.user.UsersBaseP.internal_static_cn_mxz_protocols_user_UserBaseSPro_fieldAccessorTable;
    }
    
    // repeated .cn.mxz.protocols.user.UserBasePro users = 1;
    public static final int USERS_FIELD_NUMBER = 1;
    private java.util.List<cn.mxz.protocols.user.UserBaseP.UserBasePro> users_;
    @Override
	public java.util.List<cn.mxz.protocols.user.UserBaseP.UserBasePro> getUsersList() {
      return users_;
    }
    @Override
	public java.util.List<? extends cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder> 
        getUsersOrBuilderList() {
      return users_;
    }
    @Override
	public int getUsersCount() {
      return users_.size();
    }
    @Override
	public cn.mxz.protocols.user.UserBaseP.UserBasePro getUsers(int index) {
      return users_.get(index);
    }
    @Override
	public cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder getUsersOrBuilder(
        int index) {
      return users_.get(index);
    }
    
    private void initFields() {
      users_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    @Override
	public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      for (int i = 0; i < getUsersCount(); i++) {
        if (!getUsers(i).isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    @Override
	public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < users_.size(); i++) {
        output.writeMessage(1, users_.get(i));
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    @Override
	public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      for (int i = 0; i < users_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, users_.get(i));
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
    
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static cn.mxz.protocols.user.UsersBaseP.UserBaseSPro parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    @Override
	public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(cn.mxz.protocols.user.UsersBaseP.UserBaseSPro prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    @Override
	public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements cn.mxz.protocols.user.UsersBaseP.UserBaseSProOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return cn.mxz.protocols.user.UsersBaseP.internal_static_cn_mxz_protocols_user_UserBaseSPro_descriptor;
      }
      
      @Override
	protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return cn.mxz.protocols.user.UsersBaseP.internal_static_cn_mxz_protocols_user_UserBaseSPro_fieldAccessorTable;
      }
      
      // Construct using cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
          getUsersFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      @Override
	public Builder clear() {
        super.clear();
        if (usersBuilder_ == null) {
          users_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          usersBuilder_.clear();
        }
        return this;
      }
      
      @Override
	public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      @Override
	public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.getDescriptor();
      }
      
      @Override
	public cn.mxz.protocols.user.UsersBaseP.UserBaseSPro getDefaultInstanceForType() {
        return cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.getDefaultInstance();
      }
      
      @Override
	public cn.mxz.protocols.user.UsersBaseP.UserBaseSPro build() {
        cn.mxz.protocols.user.UsersBaseP.UserBaseSPro result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private cn.mxz.protocols.user.UsersBaseP.UserBaseSPro buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        cn.mxz.protocols.user.UsersBaseP.UserBaseSPro result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      @Override
	public cn.mxz.protocols.user.UsersBaseP.UserBaseSPro buildPartial() {
        cn.mxz.protocols.user.UsersBaseP.UserBaseSPro result = new cn.mxz.protocols.user.UsersBaseP.UserBaseSPro(this);
        int from_bitField0_ = bitField0_;
        if (usersBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            users_ = java.util.Collections.unmodifiableList(users_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.users_ = users_;
        } else {
          result.users_ = usersBuilder_.build();
        }
        onBuilt();
        return result;
      }
      
      @Override
	public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof cn.mxz.protocols.user.UsersBaseP.UserBaseSPro) {
          return mergeFrom((cn.mxz.protocols.user.UsersBaseP.UserBaseSPro)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(cn.mxz.protocols.user.UsersBaseP.UserBaseSPro other) {
        if (other == cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.getDefaultInstance()) return this;
        if (usersBuilder_ == null) {
          if (!other.users_.isEmpty()) {
            if (users_.isEmpty()) {
              users_ = other.users_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureUsersIsMutable();
              users_.addAll(other.users_);
            }
            onChanged();
          }
        } else {
          if (!other.users_.isEmpty()) {
            if (usersBuilder_.isEmpty()) {
              usersBuilder_.dispose();
              usersBuilder_ = null;
              users_ = other.users_;
              bitField0_ = (bitField0_ & ~0x00000001);
              usersBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getUsersFieldBuilder() : null;
            } else {
              usersBuilder_.addAllMessages(other.users_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      @Override
	public final boolean isInitialized() {
        for (int i = 0; i < getUsersCount(); i++) {
          if (!getUsers(i).isInitialized()) {
            
            return false;
          }
        }
        return true;
      }
      
      @Override
	public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder subBuilder = cn.mxz.protocols.user.UserBaseP.UserBasePro.newBuilder();
              input.readMessage(subBuilder, extensionRegistry);
              addUsers(subBuilder.buildPartial());
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // repeated .cn.mxz.protocols.user.UserBasePro users = 1;
      private java.util.List<cn.mxz.protocols.user.UserBaseP.UserBasePro> users_ =
        java.util.Collections.emptyList();
      private void ensureUsersIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          users_ = new java.util.ArrayList<cn.mxz.protocols.user.UserBaseP.UserBasePro>(users_);
          bitField0_ |= 0x00000001;
         }
      }
      
      private com.google.protobuf.RepeatedFieldBuilder<
          cn.mxz.protocols.user.UserBaseP.UserBasePro, cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder, cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder> usersBuilder_;
      
      @Override
	public java.util.List<cn.mxz.protocols.user.UserBaseP.UserBasePro> getUsersList() {
        if (usersBuilder_ == null) {
          return java.util.Collections.unmodifiableList(users_);
        } else {
          return usersBuilder_.getMessageList();
        }
      }
      @Override
	public int getUsersCount() {
        if (usersBuilder_ == null) {
          return users_.size();
        } else {
          return usersBuilder_.getCount();
        }
      }
      @Override
	public cn.mxz.protocols.user.UserBaseP.UserBasePro getUsers(int index) {
        if (usersBuilder_ == null) {
          return users_.get(index);
        } else {
          return usersBuilder_.getMessage(index);
        }
      }
      public Builder setUsers(
          int index, cn.mxz.protocols.user.UserBaseP.UserBasePro value) {
        if (usersBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureUsersIsMutable();
          users_.set(index, value);
          onChanged();
        } else {
          usersBuilder_.setMessage(index, value);
        }
        return this;
      }
      public Builder setUsers(
          int index, cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder builderForValue) {
        if (usersBuilder_ == null) {
          ensureUsersIsMutable();
          users_.set(index, builderForValue.build());
          onChanged();
        } else {
          usersBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      public Builder addUsers(cn.mxz.protocols.user.UserBaseP.UserBasePro value) {
        if (usersBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureUsersIsMutable();
          users_.add(value);
          onChanged();
        } else {
          usersBuilder_.addMessage(value);
        }
        return this;
      }
      public Builder addUsers(
          int index, cn.mxz.protocols.user.UserBaseP.UserBasePro value) {
        if (usersBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureUsersIsMutable();
          users_.add(index, value);
          onChanged();
        } else {
          usersBuilder_.addMessage(index, value);
        }
        return this;
      }
      public Builder addUsers(
          cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder builderForValue) {
        if (usersBuilder_ == null) {
          ensureUsersIsMutable();
          users_.add(builderForValue.build());
          onChanged();
        } else {
          usersBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      public Builder addUsers(
          int index, cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder builderForValue) {
        if (usersBuilder_ == null) {
          ensureUsersIsMutable();
          users_.add(index, builderForValue.build());
          onChanged();
        } else {
          usersBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      public Builder addAllUsers(
          java.lang.Iterable<? extends cn.mxz.protocols.user.UserBaseP.UserBasePro> values) {
        if (usersBuilder_ == null) {
          ensureUsersIsMutable();
          super.addAll(values, users_);
          onChanged();
        } else {
          usersBuilder_.addAllMessages(values);
        }
        return this;
      }
      public Builder clearUsers() {
        if (usersBuilder_ == null) {
          users_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          usersBuilder_.clear();
        }
        return this;
      }
      public Builder removeUsers(int index) {
        if (usersBuilder_ == null) {
          ensureUsersIsMutable();
          users_.remove(index);
          onChanged();
        } else {
          usersBuilder_.remove(index);
        }
        return this;
      }
      public cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder getUsersBuilder(
          int index) {
        return getUsersFieldBuilder().getBuilder(index);
      }
      @Override
	public cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder getUsersOrBuilder(
          int index) {
        if (usersBuilder_ == null) {
          return users_.get(index);  } else {
          return usersBuilder_.getMessageOrBuilder(index);
        }
      }
      @Override
	public java.util.List<? extends cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder> 
           getUsersOrBuilderList() {
        if (usersBuilder_ != null) {
          return usersBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(users_);
        }
      }
      public cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder addUsersBuilder() {
        return getUsersFieldBuilder().addBuilder(
            cn.mxz.protocols.user.UserBaseP.UserBasePro.getDefaultInstance());
      }
      public cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder addUsersBuilder(
          int index) {
        return getUsersFieldBuilder().addBuilder(
            index, cn.mxz.protocols.user.UserBaseP.UserBasePro.getDefaultInstance());
      }
      public java.util.List<cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder> 
           getUsersBuilderList() {
        return getUsersFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          cn.mxz.protocols.user.UserBaseP.UserBasePro, cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder, cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder> 
          getUsersFieldBuilder() {
        if (usersBuilder_ == null) {
          usersBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              cn.mxz.protocols.user.UserBaseP.UserBasePro, cn.mxz.protocols.user.UserBaseP.UserBasePro.Builder, cn.mxz.protocols.user.UserBaseP.UserBaseProOrBuilder>(
                  users_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          users_ = null;
        }
        return usersBuilder_;
      }
      
      // @@protoc_insertion_point(builder_scope:cn.mxz.protocols.user.UserBaseSPro)
    }
    
    static {
      defaultInstance = new UserBaseSPro(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:cn.mxz.protocols.user.UserBaseSPro)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_mxz_protocols_user_UserBaseSPro_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_cn_mxz_protocols_user_UserBaseSPro_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014users_base.p\022\025cn.mxz.protocols.user\032\013u" +
      "ser_base.p\"A\n\014UserBaseSPro\0221\n\005users\030\001 \003(" +
      "\0132\".cn.mxz.protocols.user.UserBasePro"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        @Override
		public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_cn_mxz_protocols_user_UserBaseSPro_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_cn_mxz_protocols_user_UserBaseSPro_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_cn_mxz_protocols_user_UserBaseSPro_descriptor,
              new java.lang.String[] { "Users", },
              cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.class,
              cn.mxz.protocols.user.UsersBaseP.UserBaseSPro.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          cn.mxz.protocols.user.UserBaseP.getDescriptor(),
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}