package topgun.cmdline

// TODO find reference values inside JMC/JFR and replace with constants.
//  RecrodedEvent.AllocatedInNewTLAB
object RecordedEventLabelMapping {
  val AllocationInNewTLAB = "Allocation in new TLAB"
  val AllocationOutsideTlab = "Allocation outside TLAB"
  val MethodProfilingSample = "Method Profiling Sample"
  val MethodProfilingSampleNative = "Method Profiling Sample Native"
  val InitialSystemProperty = "Initial System Property"
  val RecordingSetting = "Recording Setting"
  val OSInformation = "OS Information"
}
