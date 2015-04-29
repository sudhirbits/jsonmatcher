Hamcrest Matcher Library for JSON Objects (org.json)
# Introduction #

Pages and pages of unit tests for verification of JSONObjects and inclination of using Hamcrest matchers have resulted in this.

# Details #
  1. [hasKeyMatcher](hasKeyMatcher.md)
  1. [hasPathMatcher](hasPathMatcher.md)

## Dependencies ##
  1. hamcrest
```
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
  1. google guava libraries
```
    <dependency>
          <groupId>com.google.guava</groupId>
          <artifactId>guava</artifactId>
          <version>14.0.1</version>
    </dependency>
```
  1. org.json 20090211
```
    <dependency>
          <groupId>org.json</groupId>
          <artifactId>json</artifactId>
          <version>20090211</version>
    </dependency>
```
  1. junit 4.11 (tests only)
```
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
```