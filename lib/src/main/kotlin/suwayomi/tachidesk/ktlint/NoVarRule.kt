package suwayomi.tachidesk.ktlint

/*
 * Copyright (C) Contributors to the Suwayomi project
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.pinterest.ktlint.core.Rule
import com.pinterest.ktlint.core.ast.ElementType.VAR_KEYWORD
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

public class NoVarRule : Rule("$CUSTOM_RULE_SET_ID:no-var") {
    override fun beforeVisitChildNodes(
        node: ASTNode,
        autoCorrect: Boolean,
        emit: (offset: Int, errorMessage: String, canBeAutoCorrected: Boolean) -> Unit,
    ) {
        if (node.elementType == VAR_KEYWORD) {
            emit(node.startOffset, "Unexpected var, use val instead", false)
        }
    }
}