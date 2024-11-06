package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerHelper {
	private static EntityManagerFactory entityManagerFactory;

	private static final ThreadLocal<EntityManager> entityManagerHolder;

	static {
		try {

			entityManagerFactory = Persistence.createEntityManagerFactory("eventos");

			entityManagerHolder = new ThreadLocal<EntityManager>();

		} catch (Throwable t) {
			System.err.println("Failure during static initialization: " + t.getMessage());
			t.printStackTrace();

			throw t;
		}

	}

	public static EntityManager getEntityManager() {

		EntityManager entityManager = entityManagerHolder.get();

		if (entityManager == null || !entityManager.isOpen()) {

			entityManager = entityManagerFactory.createEntityManager();

			entityManagerHolder.set(entityManager);

		}

		return entityManager;

	}

	public static void closeEntityManager() {

		EntityManager entityManager = entityManagerHolder.get();

		if (entityManager != null) {

			entityManagerHolder.set(null);

			entityManager.close();

		}

	}
}
