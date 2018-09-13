/*
 * Copyright 2012 The Closure Compiler Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.javascript.jscomp;

import java.util.List;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** @author tylerg@google.com (Tyler Goodwin) */
@RunWith(JUnit4.class)
public final class CleanupPassesTest extends TestCase {

  private final AbstractCompiler compiler = new Compiler();
  private final CompilerOptions options = new CompilerOptions();

  @Test
  public void testCleanupPassOrder() {

    CleanupPasses config = new CleanupPasses(options);

    List<PassFactory> checks = config.getChecks();

    assertTrue("Pass 0 should be a FieldCleanupPass",
        checks.get(0).create(compiler) instanceof FieldCleanupPass);
  }

  @Test
  public void testNoOptimizations() {
    CompilerOptions options = new CompilerOptions();
    CleanupPasses config = new CleanupPasses(options);
    assertTrue("Cleanup Passes unexpectedly contain optimization passes",
        config.getOptimizations().isEmpty());
  }
}
