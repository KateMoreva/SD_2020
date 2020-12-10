package training.journal.items

import training.journal.items.interfaces.WithId

data class WorkoutItem(
    override val id: String,
    val iconId: Int,
    val title: String,
    val description: String
) : WithId
