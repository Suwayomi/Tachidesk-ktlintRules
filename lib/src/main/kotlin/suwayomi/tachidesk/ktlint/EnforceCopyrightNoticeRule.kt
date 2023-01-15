package suwayomi.tachidesk.ktlint

/*
 * Copyright (C) Contributors to the Suwayomi project
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType.BLOCK_COMMENT
import com.pinterest.ktlint.core.ast.children
import com.pinterest.ktlint.core.ast.isRoot
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiCommentImpl

public class EnforceCopyrightNoticeRule : Rule("$CUSTOM_RULE_SET_ID:copyright-notice") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        var hasCopyright = false
        if (node.isRoot()) {
            val rootNode = node
            if (rootNode.textLength == 0) {
                stopTraversalOfAST()
                return
            }

            for (node in rootNode.children()) {
                if (node.elementType == BLOCK_COMMENT) {
                    hasCopyright = hasCopyright or (node as PsiCommentImpl).text.contains("Copyright (C) Contributors to the Suwayomi project")
                }
            }
            if (!hasCopyright)
                emit(rootNode.startOffset, "Copyright notice not found!", false)
            stopTraversalOfAST()
        }
    }
}