package xyz.blueju.blueshutter.domain.model.message

enum class RequestBehavior {
    NONE,  // fire and forget
    REQUIRE_ACK,
    REQUIRE_RESPONSE,
    REQUIRE_ACK_AND_RESPONSE
}

