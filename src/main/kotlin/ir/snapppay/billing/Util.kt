package ir.snapppay.billing

import kotlin.random.Random

class Util {
    companion object {
        val usernames = listOf(
            "m.malvandi", "a.mohammadi", "m.akbari", "am.jalili", "m.ghazanfari",
            "t.gouyandeh", "m.atayei", "r.mohammadi", "p.mohammadi", "e.farahani", "sh.vaziri", "m.movahed", "m.nakhaei"
        )

        fun getRandomUsername(): String = usernames[Random.nextInt(usernames.count())]

        fun getCurrentUsername(): String = usernames[0]
    }
}