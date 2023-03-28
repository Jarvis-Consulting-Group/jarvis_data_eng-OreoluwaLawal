package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc{
    @Override
    public boolean matchJpeg(String filename) {
        String filenameLowerCase = filename.toLowerCase();
        if(filenameLowerCase.matches(".*\\.(jpg|jpeg)")) {
            return true;
        }

        return false;
    }

    @Override
    public boolean matchIp(String ip) {
        if(ip.matches("\\d{1,3}.\\d{1,3}.\\d{1,3}"))
            return true;
        return false;
    }

    @Override
    public boolean isEmptyLine(String line) {
        if(line.matches("^\\s*$"))
            return true;
        return false;
    }

    public static void main(String[] args){
        RegexExcImp regexExcImp = new RegexExcImp();
        regexExcImp.matchJpeg("oreo.jpg");
        regexExcImp.matchIp("0.0");
        regexExcImp.matchIp("");
    }
}
