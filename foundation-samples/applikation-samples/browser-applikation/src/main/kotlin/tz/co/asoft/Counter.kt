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
private class CounterState(var value: Int) : State

private val Counter = fc<CounterProps> { props ->
    val counter by useState(CounterState(props.start))
    div {
        styledButton {
            css { padding(horizontal = 4.px) }
            attrs.onClickFunction = { counter.value-- }
        }
        +"-"
        styledSpan {
            css { padding(horizontal = 4.px) }
            +counter.value.toString()
        }
        styledButton {
            css { padding(horizontal = 4.px) }
            attrs.onClickFunction = { counter.value++ }
            +"+"
        }
    }
}

@ReactDsl
fun RBuilder.Counter(startAt: Int = 0) = child(Counter, CounterProps(startAt))