package com.company.tictacapp.common.models
enum class PlayerType {
    x, o, nobody
}

enum class PlayerChoice {
    x, o
}

class Player(var type: PlayerType) {
}