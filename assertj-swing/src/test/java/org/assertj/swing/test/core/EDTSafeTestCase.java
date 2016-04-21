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
package org.assertj.swing.test.core;

import org.assertj.swing.annotation.RunsInCurrentThread;
import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.hierarchy.ComponentHierarchy;
import org.assertj.swing.hierarchy.ExistingHierarchy;
import org.junit.After;
import org.junit.BeforeClass;

import javax.annotation.Nonnull;
import java.awt.Container;
import java.awt.Window;

import static org.assertj.swing.edt.GuiActionRunner.execute;

/**
 * Base test case that ensures that Swing components are created and accessed in the EDT.
 * 
 * @author Alex Ruiz
 */
public abstract class EDTSafeTestCase {

  @After
  public void tearDown() throws Exception {
    disposeWindows(new ExistingHierarchy());
  }

  @RunsInEDT
  private static void disposeWindows(final @Nonnull ComponentHierarchy hierarchy) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        for (Container c : hierarchy.roots()) {
          if (c instanceof Window) {
            dispose(hierarchy, (Window) c);
          }
        }
      }
    });
  }

  @RunsInCurrentThread
  private static void dispose(final @Nonnull ComponentHierarchy hierarchy, @Nonnull Window w) {
    hierarchy.dispose(w);
    w.setVisible(false);
    w.dispose();
  }

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }
}
