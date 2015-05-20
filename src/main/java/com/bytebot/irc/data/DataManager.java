package com.bytebot.irc.data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by AEnterprise
 */
public class DataManager {
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private Map<String, IDataHolder> dataHolders = new HashMap<String, IDataHolder>();

	public <T extends IDataHolder> T getData(Class <T> clas) {
		try {
			T holder = clas.newInstance();
			File file = new File(holder.getName() + ".json");
			if (!file.exists()) {
				holder.setDefaults();
			} else {
				holder = gson.fromJson(new FileReader(file), clas);
			}
			dataHolders.put(holder.getName(), holder);
			return holder;
		} catch (Throwable t) {
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println("Something went wrong while trying to load data for " + clas.getName());
			t.printStackTrace();
			System.out.println("-----------------------------------");
			System.out.println();
		}
		return null;
	}

	public void saveAll() {
		for (String name : dataHolders.keySet())
			save(name);
	}

	public void save(String data) {
		IDataHolder dataHolder = dataHolders.get(data);
		try {
			File file = new File(data + ".json");
			Writer writer = new FileWriter(file);
			writer.write(gson.toJson(dataHolder));
			writer.close();
		} catch (Throwable t) {
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println("Something went wrong while trying to save data for " + data);
			t.printStackTrace();
			System.out.println("-----------------------------------");
			System.out.println();
		}
	}

}
