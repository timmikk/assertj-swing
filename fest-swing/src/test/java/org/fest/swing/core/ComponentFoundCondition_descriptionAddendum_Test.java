/*
 * Created on Feb 11, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.BasicComponentFinder.finderWithNewAwtHierarchy;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.timing.Pause.pause;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.test.swing.TestWindow;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentFoundCondition#descriptionAddendum()}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentFoundCondition_descriptionAddendum_Test {

  private static ComponentFinder finder;
  private static GenericTypeMatcher<JButton> matcher;
  private static ComponentFoundCondition condition;

  @BeforeClass
  public static void setUpOnce() {
    finder = finderWithNewAwtHierarchy();
    MyWindow.createNew();
    matcher = new GenericTypeMatcher<JButton>(JButton.class) {
      protected boolean isMatching(JButton button) {
        return true;
      }
    };
    condition = new ComponentFoundCondition("JButton to be found", finder, matcher);
  }

  @Test
  public void should_append_component_hierarchy_to_exception_message_if_component_not_found() {
    try {
      pause(condition, 10);
      failWhenExpectingException();
    } catch (WaitTimedOutError e) {
      assertThat(e.getMessage()).contains("Timed out waiting for JButton to be found")
                                .contains("MyWindow[name='myWindow'")
                                .contains("javax.swing.JLabel[name=null, text='Hello'");
    }
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ComponentFoundCondition.class);
      setName("myWindow");
      addComponents(new JLabel("Hello"));
    }
  }
}