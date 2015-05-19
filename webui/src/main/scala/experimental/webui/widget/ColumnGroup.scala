package experimental.webui.widget

case class ColumnGroup[T](
    title: String,
    columns: Columns[T]
)