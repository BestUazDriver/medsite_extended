package org.example.config;

public class Property<T> {

	private String name;

	private Class<T> clazz;

	private T defaultValue;

	public Property(String name, Class<T> clazz, T defaultValue) {
		super();
		this.name = name;
		this.clazz = clazz;
		this.defaultValue = defaultValue;
	}

	public static Property<String> getInstance(String name, String defaultValue) {
		return new Property<>(name, String.class, defaultValue);
	}

	public static Property<Integer> getInstance(String name, Integer defaultValue) {
		return new Property<>(name, Integer.class, defaultValue);
	}

	public static Property<Long> getInstance(String name, Long defaultValue) {
		return new Property<>(name, Long.class, defaultValue);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "StartpackProperty [name=" + name + ", clazz=" + clazz + ", defaultValue=" + defaultValue + "]";
	}

}
