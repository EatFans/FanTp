# FanTp 饭式传送插件

切换为中文文档 [中文README](doc/README_CN.md)

## Plugin Introduction
FanTp is a lightweight Minecraft teleportation plugin that provides simple and easy-to-use player-to-player teleportation functionality for servers. Through an intuitive GUI interface and simple command system, players can easily send teleport requests to other players.

## Plugin Information
- Author：EatFan
- version：v1.1.2
- Supported Version：1.8.x - 1.21.x
- Required java Version：java8以上版本
- Supported Server：Spigot、Paper...

## Features
- Easy User ：只需输入命令或点击按钮即可发起传送请求
- Teleport Request System ：Players can send teleport requests to other players
- Graphical Interface ：Intuitive GUI menu showing all online players
- Pagination ：Support for multi-page player lists
- Request Timeout ：Teleport requests will expire after a certain time
- Permission Control ：Full permission system, allowing you to control who can use the teleportation feature
- Flexible Configuration ：Customizable messages, timeout times, etc.
## Commands
| Command       | Description     | Permission  |
|--------------|----------------|-------------|
| /tpa 或 /t    | open tp menu   | fantp.tp    |
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
no-permission: "&c没有权限！"
# Configuration reload
reload: "&a配置文件已经重新加载！"
# Open teleport menu
open-tp-menu: "&a打开了传送菜单！"
# Close teleport menu
close-tp-menu: "&c关闭了传送菜单！"
# Teleport menu container name
tp-menu-name: "&a&l传送列表"
# Player head lore in teleport menu
tp-menu-head-lore:
  - " "
  - " &e点击传送到该玩家"
  - " "
# Previous page button in teleport menu
tp-menu-last-button: "上一页"
# Next page button in teleport menu
tp-menu-next-button: "下一页"
# Close button in teleport menu
tp-menu-close-button: "关闭"
# Text for already on first page
is-first-page: "&c已经是第一页了！"
# Text for already on last page
is-end-page: "&c已经是最后一页了！"
# Sender's teleport request has expired
tp-request-timeout-a: "&c你发送的传送请求已经过期"
# Receiver's teleport request has expired
tp-request-timeout-b: "&c你有个传送请求已经过期"
# Target has unprocessed teleport request
not-processed-tp-request: "&c对方存在没处理的传送请求！无法传送！"
# No teleport request to process
no-tp-request: "&c不存在需要处理的传送请求！"
# Target accepted your teleport request
other-agree-tp-request: "&a对方同意了你的传送请求！"
# Target rejected your teleport request
other-deny-tp-request: "&c对方拒绝了你的传送请求！"
# You accepted teleport request
agree-tp-request: "&a已经同意传送请求！"
# You rejected teleport request
deny-tp-request: "&c已经拒绝传送请求！"
# Teleport request sent
send-tp-request: "&a已经发送传送请求！"
# Someone sent you a teleport request
receive-tp-request: " &a请求传送到你的位置，是否接受？"
# Chat accept button
chat-agree-button: "[ 同意 ]"
# Accept button hover text
chat-agree-button-hover: "点击以接受传送请求"
# Chat reject button
chat-deny-button: "[ 拒绝 ]"
# Reject button hover text
chat-deny-button-hover: "点击以拒绝传送请求"
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