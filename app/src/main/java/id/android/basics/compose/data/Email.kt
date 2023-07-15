package id.android.basics.compose.data

/**
 * A simple data class to represent an Email
 */
data class Email(
  val id: Long,
  val sender: Account,
  val recipients: List<Account> = emptyList(),
  val subject: String = "",
  val body: String = "",
  val attachments: List<EmailAttachment> = emptyList(),
  var isImportant: Boolean = false,
  var isStarred: Boolean = false,
  var mailbox: MailboxType = MailboxType.INBOX,
  var createAt: String,
  val threads: List<Email> = emptyList()
)