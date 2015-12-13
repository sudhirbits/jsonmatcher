# JSON matchers based on hamcrest matcher framework (for org.json)

Hamcrest framework extension to add support for JSON. The target is to build a custom factory of Matcher<JSONObjects> to aid in unit tests and production code.

Introduction
Pages and pages of unit tests for verification of JSONObjects and inclination of using Hamcrest matchers have resulted in this.

## Details
1. hasKeyMatcher
2. hasPathMatcher

## Dependencies
1. hamcrest
```xml
    <dependency>
          <groupId>org.hamcrest</groupId>
          <artifactId>hamcrest-core</artifactId>
          <version>1.3</version>
    </dependency>
    <dependency>
          <groupId>org.hamcrest</groupId>
          <artifactId>hamcrest-library</artifactId>
          <version>1.3</version>
    </dependency>
```
2. google guava libraries
```xml 
    <dependency>
          <groupId>com.google.guava</groupId>
          <artifactId>guava</artifactId>
          <version>14.0.1</version>
    </dependency>
```
3. org.json 20090211
```xml 
    <dependency>
          <groupId>org.json</groupId>
          <artifactId>json</artifactId>
          <version>20090211</version>
    </dependency>
```
4. junit 4.11 (tests only)
```xml 
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
```
