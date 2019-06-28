public enum Sites {

    AZLYRICS(1, "azlyrics", "http", "www.azlyrics.com"), SONGMEANINGS(2,
            "songmeanings", "http", "songmeanings.com"), LYRICS(3, "lyrics",
            "http", "www.lyrics.com"), METROLYRICS(4, "metrolyrics", "http",
            "www.metrolyrics.com"),NIC(5,"nicmusic","http","www.nicmusic.net");

    private int id = 0;
    private String name = "";
    private String protocol = "";
    private String host = "";

    private Sites(int id, String name, String protocol, String host) {
        this.id = id;
        this.name = name;
        this.protocol = protocol;
        this.host = host;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}