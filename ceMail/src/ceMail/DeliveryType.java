package ceMail;

public enum DeliveryType {
    GROUND(0), AIR(1), PRIORITY(2), TWO_DAY(3), ONE_DAY(4);

    int urgency;

    DeliveryType(int rand) {
        urgency = rand;

    }
}
