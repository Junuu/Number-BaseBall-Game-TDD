package input

import java.io.IOException

class InputNumber : InputUtil {
    override fun inputNumber(): String {
        return readLine() ?: throw IOException()
    }
}