# 👋 About me

嗨，我是 **李骥**，青岛农业大学智能科学与技术专业在读 👋

我主要在探索 **Java 后端 × AI 应用工程化**。相比单纯调用一个模型接口，我更喜欢研究 AI 应用真正落地时那些“不太显眼但很关键”的部分：Prompt 怎么组织、上下文怎么控制、RAG 怎么让回答有依据、流式输出怎么更稳定、长任务怎么异步化、模型异常时怎么降级兜底。

眼下我正在打磨 [**QAU SmartQA**](https://github.com/aimiaa/QAU-SmartQA) —— 一个面向校园场景的智能问答系统。它尝试把校园政策、办事指南、通知公告等分散信息接入知识库，通过文档切片、向量检索、会话历史和来源追踪，让问答不只是“像是对的”，而是尽量做到**可追溯、可维护、可扩展**。


这些项目还在持续完善中。如果你也对 AI 应用、RAG、Agent、后端工程或者 AI 辅助开发感兴趣，欢迎一起交流，也欢迎看看我的项目 ⭐

## 🛠️ Tech Stack

**Backend**  
Java · Spring Boot · Spring MVC · MyBatis / MyBatis-Plus · Spring Data JPA · Redis · JWT · Docker

**AI / Agent**  
Spring AI · Prompt Engineering · RAG · Embedding · pgvector · Tool / Function Calling · Structured Output · Agent Runtime

**Frontend**  
Vue 3 · React · TypeScript · Axios / Fetch · Vite

**Database & Infra**  
PostgreSQL · MySQL · Redis Stream · Flyway · Docker Compose

**Tools I use**  
Cursor · Claude Code · OpenAI Codex · GitHub Copilot · ChatGPT / DeepSeek · Git

---

## 🌟 Spotlight — QAU SmartQA

### QAU SmartQA · 面向校园场景的 AI 智能问答系统

**A campus AI assistant built around RAG, context control, and traceable answers.**  
Spring Boot · Spring AI · PostgreSQL / pgvector · Redis · Vue 3 · SSE

**它想解决什么？**

学校官网、政策文件、办事流程和通知公告往往分散在不同入口里。对学生和老师来说，一个简单问题可能要翻很多页面、找很多文档。QAU SmartQA 想做的是：把这些信息组织成可检索的知识库，再通过 AI 问答把查询路径变短。

**我在里面做了什么？**

- 🧠 **Context Engineering** — 设计 Prompt、上下文组装、历史消息裁剪和引用来源分离，让模型回答范围更可控。
- 🔎 **RAG Pipeline** — 围绕文档解析、语义切片、Embedding、pgvector TopK 检索和重排，构建校园资料检索链路。
- 💬 **Streaming Chat** — 基于 Spring AI ChatClient 和 SSE 实现流式回复，让问答过程更接近真实对话体验。
- 🗂️ **Knowledge Base** — 设计知识库、文档、切片、同步任务、会话和回答来源等数据模型，支撑后续扩展。
- 🛡️ **Reliability** — 处理模型超时、客户端断开、上下文过长和异常降级，让 AI 调用不至于“一出错就断掉”。

---

## 🌱 What I'm exploring

我还在持续补基础，也在一边做项目一边拆问题。现在比较关注：

- AI 应用从 Demo 到真实系统之间的工程化问题
- RAG 的检索质量、上下文边界和来源可追溯
- Agent Runtime、Tool Calling、结构化输出和任务编排
- Spring Boot 后端架构、异步任务、状态机和异常处理
- 如何更好地使用 AI 工具辅助开发、调试、复盘和文档沉淀

我希望自己做的不只是“能跑的项目”，而是慢慢做出真正有用、经得起维护，也能持续迭代的 AI 应用。
