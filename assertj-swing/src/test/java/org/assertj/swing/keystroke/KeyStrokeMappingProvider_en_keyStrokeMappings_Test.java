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
package org.assertj.swing.keystroke;

import java.awt.im.InputContext;
import java.util.Collection;
import java.util.Locale;

import javax.swing.KeyStroke;

import org.junit.Assume;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link KeyStrokeMappingProvider_en#keyStrokeMappings()}.
 * 
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class KeyStrokeMappingProvider_en_keyStrokeMappings_Test extends KeyStrokeMappingProvider_TestCase {
  public KeyStrokeMappingProvider_en_keyStrokeMappings_Test(char character, KeyStroke keyStroke) {
    super(character, keyStroke);
  }

  @Before
  public void before() {
    Assume.assumeTrue(InputContext.getInstance().getLocale() == Locale.ENGLISH);
  }

  @Parameters
  public static Collection<Object[]> keyStrokes() {
    Collection<KeyStrokeMapping> mappings = new KeyStrokeMappingProvider_en().keyStrokeMappings();
    return keyStrokesFrom(mappings);
  }
}
