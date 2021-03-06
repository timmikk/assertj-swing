/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.assertions.error;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.swing.assertions.error.ShouldHaveDimension.shouldHaveDimension;

import java.awt.Dimension;

import org.assertj.core.description.TextDescription;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ShouldHaveDimension#create(Description)}</code>.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ShouldHaveDimension_create_Test {

  private ErrorMessageFactory factory;

  @Before
  public void setUp() {
    factory = shouldHaveDimension(newArrayList("Luke", "Yoda"), new Dimension(12, 20), new Dimension(42, 15));
  }

  @Test
  public void should_Create_Error_Message() {
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    assertEquals("[Test] expected size:<42x15> but was:<12x20> in:<[\"Luke\", \"Yoda\"]>", message);
  }
}
