# FanTp 饭式传送插件

切换为中文文档 [中文README](doc/README_CN.md)

## Plugin Introduction
FanTp is a lightweight Minecraft teleportation plugin that provides simple and easy-to-use player-to-player teleportation functionality for servers. Through an intuitive GUI interface and simple command system, players can easily send teleport requests to other players.

## Plugin Information
- Author：EatFan
- version：v1.1.2
- Supported Version：1.12.x - 1.21.x
- Required java Version：java8以上版本
- Supported Server：Spigot、Paper...

## Features
- Teleport Request System ：Players can send teleport requests to other players
- Graphical Interface ：Intuitive GUI menu showing all online players
- Pagination ：Support for multi-page player lists
- Request Timeout ：Teleport requests will expire after a certain time
- Permission Control ：Full permission system, allowing you to control who can use the teleportation feature
- Flexible Configuration ：Customizable messages, timeout times, etc.
## Commands
| Command       | Description     | Permission  |
|--------------|----------------|-------------|
| /tpa or /t    | open tp menu   | fantp.tp    |
| /tpa yes      | agree tp request    | null        |
| /tpa no       | deny tp request    | null        |
| /fantp reload | reload config    | fantp.admin |


## Configuration Files
Configuration files are located in the plugin folder. You can customize messages, timeout settings, etc. according to your needs.

**config.yml**
```yaml
# Tp Request timeout
request-timeout: 20
```

lang.yml
```yaml
# Prefix
prefix: "&f&l[ &eTp &f&l]  "
# No permission
no-permission: "&cNo permission!"
# Configuration reload
reload: "&aConfiguration file has been reloaded!"
# Open teleport menu
open-tp-menu: "&aOpened teleport menu!"
# Close teleport menu
close-tp-menu: "&cClosed teleport menu!"
# Teleport menu container name
tp-menu-name: "&a&lTeleport List"
# Player head lore in teleport menu
tp-menu-head-lore:
  - " "
  - " &eClick to teleport to this player"
  - " "
# Previous page button in teleport menu
tp-menu-last-button: "Previous"
# Next page button in teleport menu
tp-menu-next-button: "Next"
# Close button in teleport menu
tp-menu-close-button: "Close"
# Text for already on first page
is-first-page: "&cAlready on the first page!"
# Text for already on last page
is-end-page: "&cAlready on the last page!"
# Sender's teleport request has expired
tp-request-timeout-a: "&cYour teleport request has expired"
# Receiver's teleport request has expired
tp-request-timeout-b: "&cA teleport request to you has expired"
# Target has unprocessed teleport request
not-processed-tp-request: "&cTarget has an unprocessed teleport request! Cannot teleport!"
# No teleport request to process
no-tp-request: "&cNo teleport request to process!"
# Target accepted your teleport request
other-agree-tp-request: "&aTarget accepted your teleport request!"
# Target rejected your teleport request
other-deny-tp-request: "&cTarget rejected your teleport request!"
# You accepted teleport request
agree-tp-request: "&aAccepted teleport request!"
# You rejected teleport request
deny-tp-request: "&cRejected teleport request!"
# Teleport request sent
send-tp-request: "&aTeleport request sent!"
# Someone sent you a teleport request
receive-tp-request: " &arequests to teleport to your location, accept?"
# Chat accept button
chat-agree-button: "[ Accept ]"
# Accept button hover text
chat-agree-button-hover: "Click to accept teleport request"
# Chat reject button
chat-deny-button: "[ Reject ]"
# Reject button hover text
chat-deny-button-hover: "Click to reject teleport request"
```

## Installation
1. Download the latest plugin file
2. Place the plugin file in your Minecraft server's plugins folder
3. Restart the server

## Update Log

### v1.1.2
- Fixed compatibility issues with versions before 1.13
- Fixed known bugs
- Optimized some code

### v1.1.1
- Added custom plugin language file
- Fixed some known bugs

### v1.0.9
- Added teleport request timeout mechanism
- Fixed some known bugs

### v1.0.7
- Fixed an issue in v1.0.6 where players who processed a teleport request and then used commands or chat buttons to process the request again caused the sender to be null, resulting in server errors

### v1.0.6
- First official release
