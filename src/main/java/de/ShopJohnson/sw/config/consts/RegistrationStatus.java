package de.ShopJohnson.sw.config.consts;

/**
 * Enum to convert registration status to string
 */
public enum RegistrationStatus {
    FAILED {
        public String toString() {
            return "Registration failed";
        }
    },
    SUCCESSFUL {
        public String toString() {
            return "Registration successful";
        }
    }
}
