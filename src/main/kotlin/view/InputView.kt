package view

class InputView {
    fun showInputGuideMessage(){
        print(InputGuideMessage)
    }

    companion object{
        private const val InputGuideMessage = "3자리 숫자를 입력해주세요 : "
    }
}