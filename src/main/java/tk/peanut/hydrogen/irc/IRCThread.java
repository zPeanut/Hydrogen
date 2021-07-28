package tk.peanut.hydrogen.irc;

public class IRCThread extends Thread {
    @Override
    public void run(){
        while(true){
            IRC.handleInput();
        }
    }
}
