package com.doctusoft.dsw.sample.client.showcase;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ExampleData {
	
	public static List<String> oldComputerNames = Lists.newArrayList(Iterables.transform(Arrays.asList(OldComputer.values()), new Function<OldComputer, String>() {
		@Override
		public String apply(OldComputer input) {
			return input.getName();
		}
	}));
	
	public static enum OldComputer {
		
		Atari400("Atari 400"),
		AtariST("Atari ST"),
		AtariTT("Atari TT"),
		Commodore64("Commodore 64"),
		CommodorePlus4("Commodore Plus/4"),
		Commodore128("Commodore 128"),
		CommodoreAmiga("Commodore Amiga"),
		Enterprise128("Enterprise 128"),
		PCJr("PCJr"),
		ZXSpectrum("ZX Spectrum");

		@Getter
		private String name;

		private OldComputer(String name) {
			this.name = name;
		}
		
	}

}
