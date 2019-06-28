public enum Engines {

    GOOGLE(1, "google", "http", "www.google.com");
    // , YAHOO(2, "yahoo", "http",
    // "www.yahoo.com"), BING(3, "bing", "http", "www.bing.com");

    private int id = 0;
    private String name = "";
    private String protocol = "";
    private String host = "";

    private Engines(int id, String name, String protocol, String host) {
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