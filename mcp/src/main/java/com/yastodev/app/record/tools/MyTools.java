package com.yastodev.app.record.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
public class MyTools {

    private static final Logger log = LoggerFactory.getLogger(MyTools.class);

    // tool
    @McpTool
    public String getLatestVideos() {

        var videos = """
    - **Spring AI 1.1.0 is HERE! Google Gemini Integration Made EASY (No More Vertex!)** – Oct

    https://www.youtube.com/watch?v=PibEG9A6AeE

    - **Stop Using Outdated AI! Add Web Search to Your Spring AI Applications (OpenAI Tutorial)**

    https://www.youtube.com/watch?v=DQt95vgtgFw

    - **Spring AI Logging Made Simple: See Every Request & Response to Your LLM** – Sep 10

    https://www.youtube.com/watch?v=R_BXvIKrN4c
    """;

        return videos;
    }


}
