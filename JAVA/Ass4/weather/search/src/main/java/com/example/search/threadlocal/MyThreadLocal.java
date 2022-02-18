package com.example.search.threadlocal;


public class MyThreadLocal {
       public static final ThreadLocal<String> userThreadPool = new ThreadLocal<String>();
       public static void startTransaction(String generatedId){
           userThreadPool.set(generatedId);
       }
       public static String getTransactionId(){
           return userThreadPool.get();
       }
       public static void endTransaction(){
           userThreadPool.remove();
       }
}
