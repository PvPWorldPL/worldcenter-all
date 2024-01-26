package pl.textr.lobby.ping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

public class ServerPing {

    private InetSocketAddress host;
    private int timeout = 2000;
    private final static Gson gson = new Gson();

    public void setAddress(InetSocketAddress host) {
        this.host = host;
    }

    @SuppressWarnings({"resource", "unused"})
    public DefaultResponse fetchData() throws IOException {
        Socket socket = new Socket();
        OutputStream outputStream;
        InputStream inputStream;
        DefaultResponse response;

        socket.setSoTimeout(timeout);
        socket.connect(host, timeout);

        outputStream = socket.getOutputStream();
        DataOutputStream dataOut = new DataOutputStream(outputStream);

        inputStream = socket.getInputStream();
        DataInputStream dataIn = new DataInputStream(inputStream);

        // HANDSHAKE >
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DataOutputStream handshake = new DataOutputStream(bOut);
        bOut.write(0x00); // packet id
        writeVarInt(handshake, 4); // protocol version
        writeVarInt(handshake, host.getHostString().length());
        handshake.writeBytes(host.getHostString());
        handshake.writeShort(host.getPort());
        writeVarInt(handshake, 1); // target state 1

        writeVarInt(dataOut, bOut.toByteArray().length);
        dataOut.write(bOut.toByteArray());
        // < HANDSHAKE

        writeVarInt(dataOut, new byte[]{0x00}.length);
        dataOut.write(new byte[]{0x00});

        // >

        int size = readVarInt(dataIn);
        int packetId = readVarInt(dataIn);

        if (packetId != 0x00) {
            throw new IOException("Invalid packetId");
        }

        int stringLength = readVarInt(dataIn);

        if (stringLength < 1) {
            throw new IOException("Invalid string length.");
        }

        byte[] responseData = new byte[stringLength];
        dataIn.readFully(responseData);
        String jsonString = new String(responseData, Charset.forName("utf-8"));

        JsonObject jsonObject = new JsonObject();
        JsonParser parser = new JsonParser();
        jsonObject = (JsonObject) parser.parse(jsonString);
        JsonObject jsonVersion = (JsonObject) jsonObject.get("version");
        String version = jsonVersion.get("name").getAsString();
        response = new DefaultResponse();

        if (version.contains("1.9")) {
            StatusResponse_1_9 response_1_9 = ServerPing.gson.fromJson(jsonString, StatusResponse_1_9.class);
            response.description = response_1_9.getDescription().getText();
            response.favicon = response_1_9.getFavicon();
            response.players = response_1_9.getPlayers().getOnline();
            response.maxPlayers = response_1_9.getPlayers().getMax();
            response.time = response_1_9.getTime();
            response.protocol = response_1_9.getVersion().getProtocol();
            response.version = response_1_9.getVersion().getName();
        } else if (version.contains("1.10") | version.contains("1.11") | version.contains("1.12")) {
            StatusResponse_1_10 response_1_10 = ServerPing.gson.fromJson(jsonString, StatusResponse_1_10.class);
            response.description = response_1_10.getDescription().getText();
            response.players = response_1_10.getPlayers().getOnline();
            response.maxPlayers = response_1_10.getPlayers().getMax();
            response.time = response_1_10.getTime();
            response.protocol = response_1_10.getVersion().getProtocol();
            response.version = response_1_10.getVersion().getName();
        } else if (version.contains("1.13") | version.contains("1.14") | version.contains("1.15") | version.contains("1.16")|  version.contains("1.18")) {
            StatusResponse_1_13 response_1_13 = ServerPing.gson.fromJson(jsonString, StatusResponse_1_13.class);
            response.description = response_1_13.getDescription().getText();
            response.players = response_1_13.getPlayers().getOnline();
            response.maxPlayers = response_1_13.getPlayers().getMax();
            response.time = -1;
            response.protocol = response_1_13.getVersion().getProtocol();
            response.version = response_1_13.getVersion().getName();
        } else {
            StatusResponse statusResponse = ServerPing.gson.fromJson(jsonString, StatusResponse.class);
            response.description = statusResponse.getDescription();
            response.favicon = statusResponse.getFavicon();
            response.players = statusResponse.getPlayers().getOnline();
            response.maxPlayers = statusResponse.getPlayers().getMax();
            response.time = statusResponse.getTime();
            response.protocol = statusResponse.getVersion().getProtocol();
            response.version = statusResponse.getVersion().getName();
        }
        // <

        dataOut.close();
        dataIn.close();
        inputStream.close();
        socket.close();

        return response;
    }

    public class DefaultResponse {
        public String description;
        public String version;
        public String protocol;
        public String favicon;
        public int players;
        public int maxPlayers;
        public int time;

        public String getVersion() {
            return version;
        }

        public String getProtocol() {
            return protocol;
        }

        public String getFavicon() {
            return favicon;
        }

        public String getDescription() {
            return description;
        }

        public int getPlayers() {
            return players;
        }

        public int getMaxPlayers() {
            return maxPlayers;
        }

        public int getTime() {
            return time;
        }
    }

    public class StatusResponse {
        private String description;
        private Players players;
        private Version version;
        private String favicon;
        private int time;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Players getPlayers() {
            return players;
        }

        public void setPlayers(Players players) {
            this.players = players;
        }

        public Version getVersion() {
            return version;
        }

        public void setVersion(Version version) {
            this.version = version;
        }

        public String getFavicon() {
            return favicon;
        }

        public void setFavicon(String favicon) {
            this.favicon = favicon;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public class Players {
            private int max;
            private int online;
            private List<Player> sample;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getOnline() {
                return online;
            }

            public void setOnline(int online) {
                this.online = online;
            }

            public List<Player> getSample() {
                return sample;
            }

            public void setSample(List<Player> sample) {
                this.sample = sample;
            }
        }

        public class Player {
            private String name;
            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public class Version {
            private String name;
            private String protocol;

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
        }
    }

    public class StatusResponse_1_9 {
        private Players players;
        private Version version;
        private String favicon;
        private Description description;
        private int time;

        public Players getPlayers() {
            return this.players;
        }

        public Description getDescription() {
            return this.description;
        }

        public Version getVersion() {
            return this.version;
        }

        public String getFavicon() {
            return this.favicon;
        }

        public int getTime() {
            return this.time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public class Description {
            private String text;

            public String getText() {
                return this.text;
            }
        }

        public class Players {
            private int max;
            private int online;
            private List<Player> sample;

            public int getMax() {
                return this.max;
            }

            public int getOnline() {
                return this.online;
            }

            public List<Player> getSample() {
                return this.sample;
            }
        }

        public class Player {
            private String name;
            private String id;

            public String getName() {
                return this.name;
            }

            public String getId() {
                return this.id;
            }
        }

        public class Version {
            private String name;
            private String protocol;

            public String getName() {
                return this.name;
            }

            public String getProtocol() {
                return this.protocol;
            }
        }
    }

    public class StatusResponse_1_10 {
        private Players players;
        private Version version;
        private Description description;
        private int time;

        public Players getPlayers() {
            return this.players;
        }

        public Description getDescription() {
            return this.description;
        }

        public Version getVersion() {
            return this.version;
        }

        public int getTime() {
            return this.time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public class Description {
            private String text;

            public String getText() {
                return this.text;
            }
        }

        public class Players {
            private int max;
            private int online;

            public int getMax() {
                return this.max;
            }

            public int getOnline() {
                return this.online;
            }
        }

        public class Version {
            private String name;
            private String protocol;

            public String getName() {
                return this.name;
            }

            public String getProtocol() {
                return this.protocol;
            }
        }
    }

    public class StatusResponse_1_16 {
        private String description;
        private Players players;
        private Version version;

        public String getDescription() {
            return this.description;
        }

        public Players getPlayers() {
            return this.players;
        }

        public Version getVersion() {
            return this.version;
        }

        public class Players {
            private int max;
            private int online;

            public int getMax() {
                return this.max;
            }

            public int getOnline() {
                return this.online;
            }
        }

        public class Version {
            private String name;
            private String protocol;

            public String getName() {
                return this.name;
            }

            public String getProtocol() {
                return this.protocol;
            }
        }
    }

    
    public class StatusResponse_1_13 {
        private Description description;
        private Players players;
        private Version version;

        public Description getDescription() {
            return this.description;
        }

        public Players getPlayers() {
            return this.players;
        }

        public Version getVersion() {
            return this.version;
        }

        public class Description {
            private String text;

            public String getText() {
                return this.text;
            }
        }

        public class Players {
            private int max;
            private int online;

            public int getMax() {
                return this.max;
            }

            public int getOnline() {
                return this.online;
            }
        }

        public class Version {
            private String name;
            private  String protocol;

            public String getName() {
                return this.name;
            }

            public String getProtocol() {
                return this.protocol;
            }
        }
    }


    public int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5)
                throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128)
                break;
        }
        return i;
    }

    public void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.write(paramInt);
                return;
            }

            out.write(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }

}
