package framework.utils;

import org.junit.Assert;
import org.junit.Test;

import framework.utils.StringUtils;

public class StringUtilTest {

	@Test
	public void testIsBlank() {
		Assert.assertTrue(StringUtils.isBlank(null));
		Assert.assertTrue(StringUtils.isBlank(' '));
		Assert.assertTrue(StringUtils.isBlank('\r'));
		Assert.assertTrue(StringUtils.isBlank('\n'));
		Assert.assertFalse(StringUtils.isBlank('A'));
		Assert.assertFalse(StringUtils.isBlank('3'));
		Assert.assertFalse(StringUtils.isBlank('.'));
	}
	
	@Test
	public void testIsNotBlank() {
		Assert.assertFalse(StringUtils.isNotBlank(null));
		Assert.assertFalse(StringUtils.isNotBlank(' '));
		Assert.assertFalse(StringUtils.isNotBlank('\r'));
		Assert.assertFalse(StringUtils.isNotBlank('\n'));
		Assert.assertTrue(StringUtils.isNotBlank('A'));
		Assert.assertTrue(StringUtils.isNotBlank('3'));
		Assert.assertTrue(StringUtils.isNotBlank('.'));
	}
}
