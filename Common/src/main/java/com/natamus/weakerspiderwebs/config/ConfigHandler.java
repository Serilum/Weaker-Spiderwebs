package com.natamus.weakerspiderwebs.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.weakerspiderwebs.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry(min = 0, max = 10000) public static int breakSpiderwebDelay = 500;

	public static void initConfig() {
		configMetaData.put("breakSpiderwebDelay", Arrays.asList(
			"The delay in ms after walking in a spiderweb until it breaks."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}