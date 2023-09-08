package cores.patterns.service_locator;

class InitialContext {
    public <T> T lookup(String objectName) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(objectName);
            T newInstance = clazz.getConstructor().newInstance();
            return cast(newInstance, clazz);
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    private static <T> T cast(Object object, Class<T> clazz) {
        if (clazz.isAssignableFrom(object.getClass())) {
            return clazz.cast(object);
        }
        throw new ClassCastException("Failed to cast instance.");
    }
}
