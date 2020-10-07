# Fault Tolerant Parser Generator

## Context

This project is an experimentation to generate fault tolerant parser in any language (Java, TypeScript) from a BNF grammar. 

Once this (very hard task) will be done, the idea is to generate a LSP language server (in Java with LSP4J, in TypeScript with vscode API) from the EBNF grammar which will consume the generated fault tolerant parser.

My idea is to follow the idea from https://github.com/microsoft/tolerant-php-parser/blob/master/docs/HowItWorks.md

## Why an another again project?

Generate parser can be done with:

 * [Antlr](https://github.com/antlr/antlr4) it generates parser in several language, manage fault tolerant parser but it requires a runtime and don't provide the requirement (see following section).
 * [JavaCC21](https://github.com/javacc21/javacc21) (one goal of JavaCC21 is to support fault tolerant). It doesn't generate another language and today JavaCC21 is very linked to Java (ex : INJECT requires the CompilationUnit which is a Java feature).
 
But those 2 projects are focus on parsing and don't have the constraints to integrate the parser with LSP (inside an editor) (see Requirement in the following section).

As I say, this project is just an experimentation, so we will see if it's doable.

## Requirement

The goal is to integrate the generated parser inside a LSP, it means that as soon as user will change content in the editor, the parser must be re-created or updated (incrememntal parsing).

It means that parser:

 * must be fault tolerant
 * must be fast even with large file.
 * doesn't consume a lot of memory.

### Cancel parsing as soon as possible

Ideally, the parser must support incremental parsing, but as it's an hard task, we will see that in the future. For the moment the idea is to re-create the parser each time the user type something in the editor.

It means that parsing must be canceled as soon as possible by an another thread which have created the parser. For instance user type something (thread 1 start to parse the editor content), the user types another character (done by thread 2), this thread2 must cancel the parsing process done by thread1.

To do that the parser must support cancel support for any process step to stop the parsing quickly. (Implement the CancelChecker concept from LSP4J).

### In Node store offset instead of line/character position

Even if LSP requires line/character position for locate node, symbol, the AST will store only offset instead of line/character because for large file it will gain a lot of memory.

### In Node don't store the image

Storing the image in the node will require to do a substring or using a StringBuilder. Using substring grows a lot the memory for large file. The idea is to not store the image in the Node when it is created but Node should provide a getImage which get the image with lazy mode (the getter call substring in this case). When AST is re-created several times, it will avoid computing image although it is not used.