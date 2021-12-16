package edu.demian.controllers;

import edu.demian.service.NativeSQLRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  private final NativeSQLRunner runnerNativeSql;

  public TestController(NativeSQLRunner runnerNativeSql) {
    this.runnerNativeSql = runnerNativeSql;
  }

  @RequestMapping("/")
  public String hello() {
    return "Hello!";
  }

  @RequestMapping("/test_native_sql")
  public String testNativeSql() {
    runnerNativeSql.startTest();
    return "Test Native SQL Started!";
  }

  @RequestMapping("/handle_security")
  public String handleSecurity() {
    return "secure string";
  }

}