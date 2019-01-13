package de.ShopJohnson.sw.ui.consts;

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
