package com.sto.protobuf.proto;

public class TestPhoneProto {
    public static void main(String[] args) {
        System.out.println("======  构建一个phoneType模型  ========");
        PhoneTypeOuterClass.Person.Builder builder = PhoneTypeOuterClass.Person.newBuilder();
        PhoneTypeOuterClass.AddressBook.Builder addressBulder = PhoneTypeOuterClass.AddressBook.newBuilder();
        PhoneTypeOuterClass.Person.Builder persionBulder = PhoneTypeOuterClass.Person.newBuilder();
        addressBulder.setPeople(0, persionBulder);
        builder.setEmail("123@321.com");
        builder.setId(1);


    }
}
