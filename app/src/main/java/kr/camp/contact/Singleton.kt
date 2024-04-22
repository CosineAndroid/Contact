package kr.camp.contact

class SingletonData {
    companion object {
        private var INSTANCE: SingletonData? = null
        fun getSingleTon(): SingletonData {
            return synchronized(SingletonData::class) {
                val newInstance = INSTANCE ?: SingletonData()
                newInstance
            }
        }
    }

    fun getContactList() = contactList()
}

