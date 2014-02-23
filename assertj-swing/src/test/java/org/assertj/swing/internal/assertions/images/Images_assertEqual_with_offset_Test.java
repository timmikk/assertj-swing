/*
 * Created on Jan 24, 2010
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
 * Copyright @2011 the original author or authors.
 */
package org.assertj.swing.internal.assertions.images;

import static java.awt.Color.BLUE;
import static org.assertj.core.data.Offset.offset;
import static org.assertj.swing.assertions.data.Point.atPoint;
import static org.assertj.swing.assertions.error.ShouldBeEqualColors.shouldBeEqualColors;
import static org.assertj.swing.assertions.error.ShouldBeEqualImages.shouldBeEqualImages;
import static org.assertj.swing.assertions.error.ShouldHaveDimension.shouldHaveDimension;
import static org.assertj.swing.test.ErrorMessages.offsetIsNull;
import static org.assertj.swing.test.awt.AwtTestData.blue;
import static org.assertj.swing.test.awt.AwtTestData.fivePixelBlueImage;
import static org.assertj.swing.test.awt.AwtTestData.fivePixelYellowImage;
import static org.assertj.swing.test.awt.AwtTestData.newImage;
import static org.assertj.swing.test.awt.AwtTestData.yellow;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.data.Offset;
import org.assertj.swing.internal.assertions.Images;
import org.assertj.swing.internal.assertions.ImagesBaseTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Images#assertEqual(AssertionInfo, BufferedImage, BufferedImage, Offset)}</code>.
 * 
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
public class Images_assertEqual_with_offset_Test extends ImagesBaseTest {

  @Override
  @Before
  public void setUp() {
    super.setUp();
    offset = offset(5);
  }

  @Test
  public void should_throw_error_if_Offset_is_null() {
    thrown.expectNullPointerException(offsetIsNull());
    images.assertEqual(someInfo(), actual, actual, null);
  }

  @Test
  public void should_pass_if_images_are_equal() {
    Color similarBlue = new Color(0, 0, 250);
    images.assertEqual(someInfo(), actual, newImage(5, 5, similarBlue), offset);
  }

  @Test
  public void should_pass_if_images_are_same() {
    images.assertEqual(someInfo(), actual, actual, offset);
  }

  @Test
  public void should_pass_if_both_images_are_null() {
    images.assertEqual(someInfo(), null, null, offset);
  }

  @Test
  public void should_fail_if_actual_is_null_and_expected_is_not() {
    AssertionInfo info = someInfo();
    thrown.expect(AssertionError.class);
    try {
      images.assertEqual(someInfo(), null, fivePixelBlueImage(), offset);
    } finally {
      verifyFailureThrownWhenImagesAreNotEqual(info);
    }
  }

  @Test
  public void should_fail_if_expected_is_null_and_actual_is_not() {
    AssertionInfo info = someInfo();
    thrown.expect(AssertionError.class);
    try {
      images.assertEqual(someInfo(), actual, null, offset);
    } finally {
      verifyFailureThrownWhenImagesAreNotEqual(info);
    }
  }

  private void verifyFailureThrownWhenImagesAreNotEqual(AssertionInfo info) {
    verify(failures).failure(info, shouldBeEqualImages(offset));
  }

  @Test
  public void should_fail_if_images_have_different_size() {
    AssertionInfo info = someInfo();
    BufferedImage expected = newImage(6, 6, BLUE);
    thrown.expect(AssertionError.class);
    try {
      images.assertEqual(info, actual, expected, offset);
    } finally {
      verify(failures).failure(info, shouldHaveDimension(actual, sizeOf(actual), sizeOf(expected)));
    }
  }

  @Test
  public void should_fail_if_images_have_same_size_but_different_color() {
    AssertionInfo info = someInfo();
    BufferedImage expected = fivePixelYellowImage();
    thrown.expect(AssertionError.class);
    try {
      images.assertEqual(info, actual, expected, offset);
    } finally {
      verify(failures).failure(info, shouldBeEqualColors(yellow(), blue(), atPoint(0, 0), offset));
    }
  }
}
