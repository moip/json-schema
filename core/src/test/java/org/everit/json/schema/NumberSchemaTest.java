/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.json.schema;

import org.junit.Test;

public class NumberSchemaTest {

  @Test
  public void exclusiveMinimum() {
    NumberSchema subject = NumberSchema.builder().minimum(10.0).exclusiveMinimum(true).build();
    TestSupport.expectFailure(
            new TestSupport.Failure()
              .subject(subject)
              .expectedKeyword("exclusiveMinimum")
              .input(10)
    );
  }

  @Test
  public void maximum() {
    NumberSchema subject = NumberSchema.builder().maximum(20.0).build();
    TestSupport.expectFailure(
            new TestSupport.Failure()
              .subject(subject)
              .expectedKeyword("maximum")
              .input(21)
    );
  }

  @Test
  public void exclusiveMaximum() {
    NumberSchema subject = NumberSchema.builder().maximum(20.0).exclusiveMaximum(true).build();
    TestSupport.expectFailure(
            new TestSupport.Failure()
              .subject(subject)
              .expectedKeyword("exclusiveMaximum")
              .input(20)
    );
  }

  @Test
  public void minimumFailure() {
    NumberSchema subject = NumberSchema.builder().minimum(10.0).build();
    TestSupport.expectFailure(
            new TestSupport.Failure()
              .subject(subject)
              .expectedKeyword("minimum")
              .input(9)
    );
  }

  @Test
  public void multipleOfFailure() {
    NumberSchema subject = NumberSchema.builder().multipleOf(10).build();
    TestSupport.expectFailure(
            new TestSupport.Failure()
              .subject(subject)
              .expectedKeyword("multipleOf")
              .input(15)
    );
  }

  @Test
  public void notRequiresNumber() {
    NumberSchema.builder().requiresNumber(false).build().validate("foo");
  }

  @Test
  public void requiresIntegerFailure() {
    NumberSchema subject = NumberSchema.builder().requiresInteger(true).build();
    TestSupport.expectFailure(subject, 10.2f);
  }

  @Test
  public void requiresIntegerSuccess() {
    NumberSchema.builder().requiresInteger(true).build().validate(10);
  }

  @Test
  public void smallMultipleOf() {
    NumberSchema.builder()
        .multipleOf(0.0001)
        .build().validate(0.0075);
  }

  @Test
  public void success() {
    NumberSchema.builder()
        .minimum(10.0)
        .maximum(11.0)
        .exclusiveMaximum(true)
        .multipleOf(10)
        .build().validate(10.0);
  }

  @Test
  public void typeFailure() {
    TestSupport.expectFailure(
            new TestSupport.Failure()
              .subject(NumberSchema.builder().build())
              .expectedKeyword("type")
              .input(null)
    );
  }

  @Test
  public void longNumber() {
    NumberSchema.builder().requiresInteger(true).build().validate(Long.valueOf(4278190207L));
  }

}
