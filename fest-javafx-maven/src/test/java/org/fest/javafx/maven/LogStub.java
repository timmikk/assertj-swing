/*
 * Created on Jan 24, 2010
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
package org.fest.javafx.maven;

import org.apache.maven.plugin.logging.Log;

/**
 * Understands an implementation of <code>{@link Log}</code> that does not do anything.
 *
 * @author Alex Ruiz
 */
public class LogStub implements Log {

  public void debug(CharSequence content) {}

  public void debug(Throwable error) {}

  public void debug(CharSequence content, Throwable error) {}

  public void info(CharSequence content) {}

  public void info(Throwable error) {}

  public void info(CharSequence content, Throwable error) {}

  public void warn(CharSequence content) {}

  public void warn(Throwable error) {}

  public void warn(CharSequence content, Throwable error) {}

  public void error(CharSequence content) {}

  public void error(Throwable error) {}

  public void error(CharSequence content, Throwable error) {}

  public boolean isDebugEnabled() {
    return false;
  }

  public boolean isInfoEnabled() {
    return false;
  }

  public boolean isWarnEnabled() {
    return false;
  }

  public boolean isErrorEnabled() {
    return false;
  }
}
