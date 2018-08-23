package com.longbridge.sams;
public class SchoolContext {

  private static ThreadLocal<Long> currentTenant = new ThreadLocal<>();

  public static Long getCurrentTenant() {
    return currentTenant.get();
  }

  public static void setCurrentTenant(Long sid) {
    currentTenant.set(sid);
  }

  public static void clear() {
    currentTenant.set(null);
  }

}