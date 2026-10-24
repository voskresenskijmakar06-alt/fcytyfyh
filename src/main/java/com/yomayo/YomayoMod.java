package com.yomayo;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YomayoMod implements ModInitializer {
	public static final String MOD_ID = "yomayo";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.register();
		LOGGER.info("Йо, майо! Мод загружен.");
	}
}
