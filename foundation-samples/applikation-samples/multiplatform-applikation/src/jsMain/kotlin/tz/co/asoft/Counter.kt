package tz.co.asoft;

import kotlinx.css.padding
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.div
import styled.css
import styled.styledButton
import styled.styledSpan

private class CounterProps(val start: Int) : Props

private val Counter = fc<CounterProps> { props ->
    var counter by useState(props.start)
    div {
        styledButton {
            css { padding(horizontal = 4.px) }
            attrs.onClickFunction = { counter-- }
            +"-"
        }
        styledSpan {
            css { padding(horizontal = 4.px) }
            +counter.toString()
        }
        styledButton {
            css { padding(horizontal = 4.px) }
            attrs.onClickFunction = { counter++ }
            +"+"
        }
    }
}

fun RBuilder.Counter(startAt: Int = 0) = child(Counter, CounterProps(startAt))