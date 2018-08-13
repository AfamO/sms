package com.longbridge.sams;
public class SchoolContext {

  private static ThreadLocal<Integer> currentTenant = new ThreadLocal<>();

  public static Integer getCurrentTenant() {
    return currentTenant.get();
  }

  public static void setCurrentTenant(Integer sid) {
    currentTenant.set(sid);
  }

  public static void clear() {
    currentTenant.set(null);
  }

}