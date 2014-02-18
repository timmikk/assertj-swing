/*
 * Created on Jun 27, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2009-2013 the original author or authors.
 */
package org.assertj.swing.driver;

import static org.assertj.core.util.Preconditions.checkNotNull;
import static org.assertj.swing.edt.GuiActionRunner.execute;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JList;

import org.assertj.swing.annotation.RunsInEDT;
import org.assertj.swing.edt.GuiQuery;

/**
 * Returns the selected indices in a {@code JList}. This query is executed in the event dispatch thread (EDT.)
 * 
 * @author Alex Ruiz
 */
final class JListSelectionIndicesQuery {
  @RunsInEDT
  static @Nonnull
  int[] selectedIndices(final @Nonnull JList list) {
    int[] result = execute(new GuiQuery<int[]>() {
      @Override
      protected @Nullable
      int[] executeInEDT() {
        return list.getSelectedIndices();
      }
    });
    return checkNotNull(result);
  }

  private JListSelectionIndicesQuery() {
  }
}